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
    "oss@busti.cool"
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
  Developer("Busti", "Moritz Bust", "oss@busti.cool", url("https://github.com/busti"))
)

credentials += Credentials(Path.userHome / ".sbt" / "sonatype_credentials")