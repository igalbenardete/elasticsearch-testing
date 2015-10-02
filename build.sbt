name := "ElasticTest"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "com.sksamuel.elastic4s" %% "elastic4s-streams" % "1.7.4",
  "com.sksamuel.elastic4s" % "elastic4s-jackson_2.11" % "1.7.4",
  "com.beachape" %% "enumeratum" % "1.3.1"
)
