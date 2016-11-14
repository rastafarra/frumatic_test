name := "frumatic_test"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq (
  "com.typesafe.akka" %% "akka-actor" % "2.4.12",
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % "test")


lazy val root = (project in file(".")).enablePlugins(PlayScala)
