ThisBuild / scalaVersion := "3.3.0-RC5"

credentials += Credentials(
  "Artifactory Realm",
  "zdrepo.jfrog.io",
  sys.env.getOrElse(
    "ARTIFACTORY_USERNAME",
    throw new IllegalArgumentException("Please define [ARTIFACTORY_USERNAME] in the environment variable"),
  ),
  sys.env.getOrElse(
    "ARTIFACTORY_API_KEY",
    throw new IllegalArgumentException("Please define [ARTIFACTORY_API_KEY] in the environment variable"),
  ),
)


lazy val fpmocking = project.in(file("."))
  .settings(
    name := "FPandMocking",
    version := "0.1.0",
    libraryDependencies ++= Seq(
     "org.creativescala" %% "doodle" % "0.18.0",
    ),
  )

lazy val docs = project
  .in(file("slides"))
  .settings(
    mdocOut := file("slides")
  )
  .dependsOn(fpmocking)
  .enablePlugins(MdocPlugin)