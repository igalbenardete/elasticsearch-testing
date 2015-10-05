package enumaration

import enumeratum.{Enum, EnumEntry}

sealed trait Company extends EnumEntry{
  override def toString = getClass.getSimpleName.takeWhile(_ != '$')
}

object Company extends Enum[Company]{
  override def values: Seq[Company] = findValues

  object Cmak extends Company
  object Google extends Company
  object Apple extends Company
}
