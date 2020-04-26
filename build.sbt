import sbt.Keys.{libraryDependencies, scalaVersion, version}
lazy val root = (project in file(".")).
  settings(

    name := "MatrixMultiplication",

    version := "0.1.0",

    scalaVersion := "2.11.8",

    publishMavenStyle := true,

    //mainClass := Some("MatrixMul_Spark")
)

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "2.3.4",
  "org.apache.spark" %% "spark-sql" % "2.3.4",
  "org.apache.spark" %% "spark-mllib" % "2.3.4",

  "org.scalanlp" %% "breeze" % "1.0",
  "org.scalanlp" %% "breeze-natives" % "1.0",
  "org.scalanlp" %% "breeze-viz" % "1.0"
)
assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}
