package entity

import com.sksamuel.elastic4s.source.{DocumentMap, Indexable}
import org.elasticsearch.common.joda.time.DateTime

case class Project(supervisor: String,
                   startDate: DateTime,
                   duration: Long)
  extends DocumentMap {
  override def map: Map[String, Any] = {
    val fieldNames = this.getClass.getDeclaredFields.map(_.getName)
    val values = Project.unapply(this).get.productIterator.toSeq

    fieldNames.zip(values).toMap
  }
}

object Project {
  implicit object ProjectIndexable extends Indexable[Project] {
    override def json(t: Project): String =
      s"""
         |{
         |  "supervisor" : ${t.supervisor},
         |  "startDate" : ${t.startDate},
         |  "duration" : ${t.duration}
       """.stripMargin
  }
}