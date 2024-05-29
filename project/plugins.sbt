templateResolverInfos += TemplateResolverInfo(
  ModuleID("org.scala-sbt.sbt-giter8-resolver", "sbt-giter8-resolver_2.12", "0.15.0") cross CrossVersion.binary,
  "sbtgiter8resolver.Giter8TemplateResolver",
)

addSbtPlugin("org.scalameta"             % "sbt-scalafmt" % "2.5.2")
addSbtPlugin("org.foundweekends.giter8" %% "sbt-giter8"   % "0.16.2")
