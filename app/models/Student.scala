package models

import play.api.libs.json.{Json, OFormat}

case class Student (
                   document: Long,
                   name: String,
                   last_name: String,
                   courses: Set[Course]
                   )
