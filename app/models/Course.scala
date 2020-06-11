package models

import play.api.libs.json.Json

case class Course (
                  _id: Long,
                  name: String
                  )
object JsonFormats {
  implicit val courseFormat = Json.format[Course]
  implicit val studentFormat = Json.format[Student]
}
