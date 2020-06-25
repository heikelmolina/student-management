package repositories

import models.Course
import play.api.libs.json.{JsObject, Json}
import play.api.mvc.{AbstractController, ControllerComponents}
import play.modules.reactivemongo.{
  MongoController,
  ReactiveMongoApi,
  ReactiveMongoComponents
}
import javax.inject.Inject

import scala.concurrent.{ExecutionContext, Future}
import reactivemongo.bson.BSONDocument
import reactivemongo.play.json._
import collection._
import reactivemongo.api.Cursor
import reactivemongo.api.Cursor.WithOps

class CourseRepository @Inject()(val components: ControllerComponents,
                                 val reactiveMongoApi: ReactiveMongoApi)
    extends AbstractController(components)
    with MongoController
    with ReactiveMongoComponents {

  implicit def ec: ExecutionContext = components.executionContext

  def collection: Future[JSONCollection] =
    database.map(_.collection[JSONCollection]("courses"))

  def create(course: Course) =
    collection.flatMap(_.insert.one(course))

  def createAll(courses: List[Course]) =
    collection.flatMap(_.insert.many(courses))

//  def find() = {
//    val cursor: Future[Cursor[Course]] = collection.map {
//      _.find(BSONDocument()).cursor[Course]()
//    }
//
//    val futureCoursesList =
//      cursor.flatMap(_.collect[List](-1, Cursor.FailOnError[List[Course]]()))
//    futureCoursesList
//  }
//
//  def findId(id: Long) = {
//    val cursor: Future[Cursor[Course]] = collection.map {
//      _.find(Json.obj("_id" -> id)).cursor[Course]()
//    }
//
//    val futureCoursesList: Future[List[Course]] =
//      cursor.flatMap(_.collect[List](-1, Cursor.FailOnError[List[Course]]()))
//    futureCoursesList
//  }

  def find(idOpt: Option[Long]) = {
    val a: Future[WithOps[Course]] = idOpt match {
      case Some(x) =>
        collection.map {
          _.find(Json.obj("_id" -> x)).cursor[Course]()
        }
      case _ =>
        collection.map {
          _.find(BSONDocument()).cursor[Course]()
        }
    }

    a.flatMap(_.collect[List](-1, Cursor.FailOnError[List[Course]]()))
  }
}
