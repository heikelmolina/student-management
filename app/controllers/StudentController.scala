package controllers

import models.Student
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, ControllerComponents}
import javax.inject.Inject

import scala.concurrent.{ExecutionContext, Future}
import javax.inject.Singleton
import repositories.StudentRepository

@Singleton
class StudentController @Inject()(val components: ControllerComponents,
                                  val studentRepository: StudentRepository)
    extends AbstractController(components) {

  implicit def ec: ExecutionContext = components.executionContext

  def createStudent = Action.async(parse.json) {
    _.body
      .validate[Student]
      .map { student =>
        studentRepository.create(student).map { _ =>
          Ok(Json.toJson(student))
        }
      }
      .getOrElse(Future.successful(BadRequest("Invalid format")))
  }

  def allStudents() = Action.async {
    studentRepository.find().map { student =>
      Ok(Json.toJson(student))
    }
  }

  def findStudentByDocument(document: Long) = Action.async {
    studentRepository.findId(document).map { student =>
      Ok(Json.toJson(student))
    }
  }
}
