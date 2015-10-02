package entity

import com.sksamuel.elastic4s.source.Indexable
import enumaration.Company

case class Employee(name: String,
                    age: Int,
                    company: Company,
                    manager: Option[Employee])
  extends Person

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