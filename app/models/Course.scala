package models

import play.api.libs.json.Json

case class Course(_id: Long, name: String)

object Course {
  implicit val courseFormat = Json.format[Course]
}
