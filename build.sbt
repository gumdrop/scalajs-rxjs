
lazy val commonSettings = Seq(
  organization := "de.surfice",
  version := "0.1-SNAPSHOT",
  scalaVersion := "2.11.7",
  scalacOptions ++= Seq("-deprecation","-unchecked","-feature","-Xlint"),
  libraryDependencies ++= Seq(
  ),
  scalacOptions ++= (if (isSnapshot.value) Seq.empty else Seq({
        val a = baseDirectory.value.toURI.toString.replaceFirst("[^/]+/?$", "")
        val g = "https://raw.githubusercontent.com/jokade/scalajs-rxjs"
        s"-P:scalajs:mapSourceURI:$a->$g/v${version.value}/"
      }))
)


lazy val root = project.in(file(".")).
  enablePlugins(ScalaJSPlugin).
  settings(commonSettings: _*).
  settings(publishingSettings: _*).
  //settings(sonatypeSettings: _*).
  settings( 
    name := "scalajs-rxjs",
    libraryDependencies ++= Seq(
      "com.lihaoyi" %%% "utest" % "0.3.1"
    ),
    jsDependencies ++= Seq(
      "org.webjars.npm" % "rxjs" % "5.0.0-alpha.14" / "bundles/Rx.umd.js" % "test"
      //"org.webjars.bower" % "rxjs" % "4.0.6" / "rx.all.min.js" % "test"
    ),
    testFrameworks += new TestFramework("utest.runner.Framework"),
    resolvers += Resolver.sonatypeRepo("releases")
  )


lazy val publishingSettings = Seq(
  publishMavenStyle := true,
  publishTo := {
    val nexus = "https://oss.sonatype.org/"
    if (isSnapshot.value)
      Some("snapshots" at nexus + "content/repositories/snapshots")
    else
      Some("releases"  at nexus + "service/local/staging/deploy/maven2")
  },
  pomExtra := (
    <url>https://github.com/jokade/scalajs-rxjs</url>
    <licenses>
      <license>
        <name>MIT License</name>
        <url>http://www.opensource.org/licenses/mit-license.php</url>
      </license>
    </licenses>
    <scm>
      <url>git@github.com:jokade/scalajs-rxjs</url>
      <connection>scm:git:git@github.com:jokade/scalajs-rxjs.git</connection>
    </scm>
    <developers>
      <developer>
        <id>jokade</id>
        <name>Johannes Kastner</name>
        <email>jokade@karchedon.de</email>
      </developer>
    </developers>
  )
)
 
