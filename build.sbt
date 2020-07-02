name := """student-managment"""
organization := "student.managment"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.1"

// Version of reactivemongo for this scalaVersion. Reactivemongo is used to connect to MongoDB
val reactiveMongoVersion = "0.18.7-play27"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test

// Reactivemongo dependency
libraryDependencies += "org.reactivemongo" %% "play2-reactivemongo" % reactiveMongoVersion

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.student.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.student.binders._"

import play.sbt.routes.RoutesKeys

RoutesKeys.routesImport += "play.modules.reactivemongo.PathBindables._"