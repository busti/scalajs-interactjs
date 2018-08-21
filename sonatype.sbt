//sonatypeProfileName := "com.github.busti"
organization        := "com.github.busti"

publishMavenStyle := true

licenses += ("MIT" -> url("https://github.com/Busti/scalajs-interactjs/blob/master/LICENSE"))

import xerial.sbt.Sonatype._

sonatypeProjectHosting := Some(
  GitHubHosting(
    "Busti",
    "scalajs-interactjs",
    "Moritz Bust",
    "mbust@live.de"
  )
)

homepage := Some(url("https://github.com/busti/scalajs-interactjs"))

scmInfo := Some(
  ScmInfo(
    url("https://github.com/busti/scalajs-interactjs"),
    "git@github.com:busti/scalajs.git"
  )
)

developers := List(
  Developer("Busti", "Moritz Bust", "mbust@live.de", url("https://github.com/busti"))
)