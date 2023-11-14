enablePlugins(ScalaJSPlugin)

val scala2 = "2.13.12"
val scala3 = "3.3.1"

name := "scalajs-interactjs"
version := "1.1.4"
crossScalaVersions := List(scala2, scala3)

libraryDependencies ++= Seq(
  "org.scala-js" %%% "scalajs-dom" % "2.8.0"
)

publishTo := sonatypePublishTo.value