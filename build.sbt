enablePlugins(ScalaJSPlugin)

name := "scalajs-interactjs"
version := "1.0.2"
scalaVersion := "2.13.8"

libraryDependencies ++= Seq(
  "org.scala-js" %%% "scalajs-dom" % "2.2.0"
)

publishTo := sonatypePublishTo.value