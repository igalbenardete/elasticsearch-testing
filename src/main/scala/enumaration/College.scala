package enumaration

import enumeratum.{Enum, EnumEntry}

sealed trait College extends EnumEntry

object College extends Enum[College] {
  override def values: Seq[College] = findValues

  object Koc extends College
  object Sabanci extends College
  object Itu extends College
  object Kocaeli extends College

}