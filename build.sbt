enablePlugins(ScalaJSPlugin)

name := "scalajs-interactjs"
version := "0.1"
scalaVersion := "2.12.6"

scalacOptions ++= Seq("-P:scalajs:sjsDefinedByDefault")

libraryDependencies ++= Seq(
  "org.scala-js" %%% "scalajs-dom" % "0.9.2"
)

publishTo := sonatypePublishTo.value