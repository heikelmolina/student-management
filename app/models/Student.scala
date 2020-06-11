package models

import play.api.libs.json.{Json, OFormat}

case class Student(document: Long,
                   name: String,
                   lastName: String,
                   courses: Set[Course])

object Student {
  implicit val studentFormat = Json.format[Student]
}
