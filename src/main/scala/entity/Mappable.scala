package entity

trait Mappable {
  def map[T <: Mappable]: Map[String, Any]
}
