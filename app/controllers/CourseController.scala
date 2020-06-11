package controllers

import models.Course
import models.JsonFormats._
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, ControllerComponents}
import play.modules.reactivemongo.{MongoController, ReactiveMongoApi, ReactiveMongoComponents}
import javax.inject.Inject

import scala.concurrent.{ExecutionContext, Future}
import reactivemongo.bson.BSONDocument
import reactivemongo.play.json._
import collection._
import reactivemongo.api.Cursor
import javax.inject.Singleton
import reactivemongo.api.commands.WriteResult


@Singleton
class CourseController @Inject()(val components: ControllerComponents, val reactiveMongoApi: ReactiveMongoApi) extends AbstractController(components)
  with MongoController with ReactiveMongoComponents {

  implicit def ec: ExecutionContext = components.executionContext

  def collection: Future[JSONCollection] = database.map(
    _.collection[JSONCollection]("courses"))

  def allcourses() = Action.async {
    find().map { courses =>
      Ok(Json.toJson(courses))
    }
  }

  //helper to move to CourseRepository
  def find() = {
    val cursor: Future[Cursor[Course]] = collection.map {
      _.find(BSONDocument()).
        cursor[Course]()
    }

    val futureUsersList: Future[List[Course]] =
      cursor.flatMap(_.collect[List](-1, Cursor.FailOnError[List[Course]]()))
    futureUsersList
  }


  def createcourse = Action.async(parse.json) {
    _.body.validate[Course].map { course =>
      create(course).map { _ => Ok(Json.toJson(course))}
    }
      .getOrElse(Future.successful(BadRequest(
        "Invalid format")))
  }

  //helper to move to CourseRepository
  def create (course: Course): Future[WriteResult] =
    collection.flatMap(_.insert.one(course))


  def findcoursebyid(id: Long) = Action.async {
    findid(id).map { course =>
      Ok(Json.toJson(course))
    }
  }

  //helper to move to CourseRepository
  def findid(id: Long) = {
    val cursor: Future[Cursor[Course]] = collection.map {
      _.find(Json.obj("_id" -> id)).
        cursor[Course]()
    }

    val futureUsersList: Future[List[Course]] =
      cursor.flatMap(_.collect[List](-1, Cursor.FailOnError[List[Course]]()))
    futureUsersList
  }
}
