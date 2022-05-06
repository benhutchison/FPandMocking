ThisBuild / scalaVersion := "3.1.2"


lazy val fpmocking = project.in(file("."))
  .settings(
    name := "FPandMocking",
    version := "0.1.0",
    libraryDependencies ++= Seq(
      "org.creativescala" %% "doodle" % "0.10.1"
    ),
  )

lazy val docs = project
  .in(file("slides"))
  .settings(
    mdocOut := file("slides")
  )
  .dependsOn(fpmocking)
  .enablePlugins(MdocPlugin)