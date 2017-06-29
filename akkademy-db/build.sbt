name := """akkademy-db"""
organization := "com.akkademy-db"
version := "0.0.1-SNAPSHOT"

scalaVersion := "2.11.7"

// Change this to another test framework if you prefer
libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.4" % "test"

// Uncomment to use Akka
libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.3.11"
libraryDependencies += "com.typesafe.akka" %% "akka-remote" % "2.5.3"
libraryDependencies += "com.typesafe.akka" %% "akka-testkit" % "2.5.3" % "test"
//libraryDependencies += "org.scala-lang.modules" %% "scala-java8-compat" % "0.6.0"
mappings in (Compile, packageBin) ~= { _.filterNot { case (_, name) => Seq("application.conf").contains(name)
}}

fork in run := true
