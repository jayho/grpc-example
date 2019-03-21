// *****************************************************************************
// Projects
// *****************************************************************************

lazy val `grpc-example-sbt` =
  project
    .in(file("."))
    .settings(settings)
    .settings(
      libraryDependencies ++= Seq(
        library.grpcNetty,
        library.scalapbRuntimeGrpc
      )
    )

// *****************************************************************************
// Library dependencies
// *****************************************************************************

lazy val library =
  new {
    object Version {
      val grpcNetty  = scalapb.compiler.Version.grpcJavaVersion
      val scalapbVer = scalapb.compiler.Version.scalapbVersion
    }
    val grpcNetty          = "io.grpc"              %  "grpc-netty"           % Version.grpcNetty
    val scalapbRuntimeGrpc = "com.thesamet.scalapb" %% "scalapb-runtime-grpc" % Version.scalapbVer
  }

// *****************************************************************************
// Settings
// *****************************************************************************

lazy val settings =
  commonSettings ++
  scalapbSettings ++
  scalafmtSettings

lazy val commonSettings =
  Seq(
    scalaVersion := "2.12.8",
    organization := "de.jh",
    organizationName := "Jens Hoffmann",
    startYear := Some(2019),
    scalacOptions ++= Seq(
      "-unchecked",
      "-deprecation",
      "-language:_",
      "-target:jvm-1.8",
      "-encoding", "UTF-8",
      "-Ypartial-unification",
      "-Ywarn-unused-import"
    )
)

lazy val scalapbSettings =
  Seq(
    Compile / PB.targets := Seq(
      scalapb.gen() -> (Compile / sourceManaged).value
    )
  )

lazy val scalafmtSettings =
  Seq(
    scalafmtOnCompile := true
  )
