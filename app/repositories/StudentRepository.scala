package repositories

import models.Student
import play.api.libs.json.Json
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

class StudentRepository @Inject()(val components: ControllerComponents,
                                  val reactiveMongoApi: ReactiveMongoApi,
                                  val courseRepository: CourseRepository)
    extends AbstractController(components)
    with MongoController
    with ReactiveMongoComponents {

  implicit def ec: ExecutionContext = components.executionContext

  def collection: Future[JSONCollection] =
    database.map(_.collection[JSONCollection]("students"))

  def create(student: Student) = {
    collection.flatMap(_.insert.one(student))
    courseRepository.createAll(student.courses)
  }

  def find() = {
    val cursor: Future[Cursor[Student]] = collection.map {
      _.find(BSONDocument()).cursor[Student]()
    }

    val futureStudentsList =
      cursor.flatMap(_.collect[List](-1, Cursor.FailOnError[List[Student]]()))
    futureStudentsList
  }

  def findId(document: Long) = {
    val cursor: Future[Cursor[Student]] = collection.map {
      _.find(Json.obj("document" -> document)).cursor[Student]()
    }

    val futureStudentsList: Future[List[Student]] =
      cursor.flatMap(_.collect[List](-1, Cursor.FailOnError[List[Student]]()))
    futureStudentsList
  }
}
