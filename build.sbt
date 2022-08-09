enablePlugins(ScalaJSPlugin)

name := "scalajs-interactjs"
version := "1.0.1"
scalaVersion := "2.13.8"

libraryDependencies ++= Seq(
  "org.scala-js" %%% "scalajs-dom" % "1.1.0"
)

publishTo := sonatypePublishTo.value