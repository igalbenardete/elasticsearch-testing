package entity

import com.sksamuel.elastic4s.source.{DocumentMap, Indexable}
import enumaration.College
import org.elasticsearch.common.joda.time.DateTime

case class Student(name: String,
                   age: Int,
                   college: College,
                   birthDate: DateTime,
                   currentProject: Project,
                   graduationStatus: Boolean)
  extends Person with DocumentMap {
  override def map: Map[String, Any] = {
    val fieldNames = this.getClass.getDeclaredFields.map(_.getName)
    val values = Student.unapply(this).get.productIterator.toSeq

    fieldNames.zip(values).toMap
  }
}

object Student {
  implicit object StudentIndexable extends Indexable[Student] {
    override def json(t: Student): String =
      s"""
         |{
         |  "name" : ${t.name},
         |  "age" : ${t.age},
         |  "college" : ${t.college},
         |  "birthDate" : ${t.birthDate},
         |  "graduationStatus" : ${t.graduationStatus}
         |}
       """.stripMargin
  }
}

