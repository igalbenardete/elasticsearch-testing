package entity


import com.sksamuel.elastic4s.source.Indexable
import enumaration.College
import org.elasticsearch.common.joda.time.DateTime

case class Student(name: String,
                   age: Int,
                   college: College,
                   birthDate: DateTime,
                   currentProject: Project,
                   graduationStatus: Boolean)
  extends Person with Mappable {
  override def map: Map[String, Any] = {
    val fieldNames = this.getClass.getDeclaredFields.map(_.getName)
    val values = Student.unapply(this).get.productIterator.map{
      case obj: Mappable => obj.map
      case x: Any => x
    }.toSeq

    fieldNames.zip(values).toMap
  }
}

object Student {
  implicit object StudentIndexable extends Indexable[Student] {
    override def json(t: Student): String =
      s"""
         |{
         |  "name" : "${t.name}",
         |  "age" : ${t.age},
         |  "college" : "${t.college}",
         |  "birthDate" : ${t.birthDate.getMillis},
         |  "currentProject" : ${t.currentProject.map},
         |  "graduationStatus" : ${t.graduationStatus}
         |}
       """.stripMargin
  }
}

