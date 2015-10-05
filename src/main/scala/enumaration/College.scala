package enumaration

import enumeratum.{Enum, EnumEntry}

sealed trait College extends EnumEntry{
  override def toString = getClass.getSimpleName.takeWhile(_ != '$')
}

object College extends Enum[College] {
  override def values: Seq[College] = findValues

  object Koc extends College
  object Sabanci extends College
  object Itu extends College
  object Kocaeli extends College

}