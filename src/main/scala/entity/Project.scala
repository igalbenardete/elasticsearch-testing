package entity

import com.sksamuel.elastic4s.source.{DocumentMap, Indexable}
import org.elasticsearch.common.joda.time.DateTime

case class Project(supervisor: String,
                   startDate: DateTime,
                   duration: Long)
  extends Mappable {
  override def map[T <: Mappable]: Map[String, Any] = {
    val fieldNames = this.getClass.getDeclaredFields.map(_.getName)
    val values = Project.unapply(this).get.productIterator.map {
      case obj: T => obj.map
      case x: Any => x
    }.toSeq

    fieldNames.zip(values).toMap
  }
}

object Project {
  implicit object ProjectIndexable extends Indexable[Project] {
    override def json(t: Project): String =
      s"""
         |{
         |  "supervisor" : "${t.supervisor}",
         |  "startDate" : "${t.startDate.getMillis}",
         |  "duration" : ${t.duration}
         |}
       """.stripMargin
  }
}
