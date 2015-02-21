name := "roborally-sc"

version := "1.0"

scalaVersion := "2.11.5"

/////////////////////// PROJECT DESCRIPTION /////////////////////////////////////

organization := "com.nalbisoft"

name         := "RoboRally"

version      := "1.0.0"

mainClass in (Compile,run) := Some("com.nalbisoft.roborally.main.RoboRally")

libraryDependencies ++= Seq(
  "org.specs2" %% "specs2" % "2.3.12" % "test"
)
