package entity

import org.elasticsearch.common.joda.time.DateTime

case class Agreement(date: DateTime,
                     isValid: Boolean)
