package controllers

import models.Course
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, ControllerComponents}
import javax.inject.Inject

import scala.concurrent.{ExecutionContext, Future}
import javax.inject.Singleton
import repositories.CourseRepository

@Singleton
class CourseController @Inject()(val components: ControllerComponents,
                                 val courseRepository: CourseRepository)
    extends AbstractController(components) {

  implicit def ec: ExecutionContext = components.executionContext

  def createCourse = Action.async(parse.json) {
    _.body
      .validate[Course]
      .map { course =>
        courseRepository.create(course).map { _ =>
          Ok(Json.toJson(course))
        }
      }
      .getOrElse(Future.successful(BadRequest("Invalid format")))
  }

  def allCourses() = Action.async {
    courseRepository.find(null).map { courses =>
      Ok(Json.toJson(courses))
    }
  }

  def findCourseById(id: Long) = Action.async {
    courseRepository.find(Some(id)).map { course =>
      Ok(Json.toJson(course))
    }
  }
}
