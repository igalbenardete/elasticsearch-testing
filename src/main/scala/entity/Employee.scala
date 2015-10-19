package entity

import com.sksamuel.elastic4s.source.Indexable
import enumaration.Company

case class Employee(name: String,
                    age: Int,
                    company: Company,
                    manager: Option[Employee])
  extends Person with Mappable {
  override def map[T <: Mappable]: Map[String, Any] = {
    val fieldNames = this.getClass.getDeclaredFields.map(_.getName)
    val values = Employee.unapply(this).get.productIterator.map{
      case optionObj: Option[T] => optionMap(optionObj)
      case obj: T => obj.map
      case x: Any => x
    }.toSeq

    fieldNames.zip(values).toMap
  }

  private def optionMap[T <: Mappable](optionObj: Option[T]) = {
    optionObj match {
      case None => null
      case Some(obj) => obj.map
    }
  }
}

object Employee {
  implicit object EmployeeIndexable extends Indexable[Employee] {
    override def json(t: Employee): String =
      s"""
         |{
         |  "name" : ${t.name},
         |  "age" : ${t.age},
         |  "company" : ${t.company},
         |  "manager" : ${t.manager}
         |}
       """.stripMargin
  }
}