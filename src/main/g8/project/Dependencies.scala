import sbt._

object Dependencies {
  lazy val gatling: Seq[ModuleID] = Seq(
    "io.gatling.highcharts" % "gatling-charts-highcharts",
    "io.gatling"            % "gatling-test-framework",
  ).map(_ % "$gatling_version$" % Test)

  lazy val gatlingPicatinny: Seq[ModuleID] = Seq("org.galaxio" %% "gatling-picatinny" % "$gatling_picatinny_version$")
  lazy val janino: Seq[ModuleID]           = Seq("org.codehaus.janino" % "janino" % "3.1.12")
  $if(amqpPlugin.truthy) $
  lazy val amqpPlugin: Seq[ModuleID]       = Seq("org.galaxio" %% "gatling-amqp-plugin" % "$gatling_amqp_version$")
  $endif$
  $if(kafkaPlugin.truthy) $
  lazy val kafkaPlugin: Seq[ModuleID]      = Seq("org.galaxio" %% "gatling-kafka-plugin" % "$gatling_kafka_version$")
  lazy val kafkaSerializer: Seq[ModuleID]  = Seq("io.confluent" % "kafka-avro-serializer" % "7.6.0")
  lazy val avro4s: Seq[ModuleID]           = Seq("com.sksamuel.avro4s" %% "avro4s-core" % "4.1.2")
  $endif$
  $if(jdbcPlugin.truthy) $
  lazy val jdbcPlugin: Seq[ModuleID]       = Seq("org.galaxio" %% "gatling-jdbc-plugin" % "$gatling_jdbc_version$")
  lazy val postgresJdbc: Seq[ModuleID]     = Seq("org.postgresql" % "postgresql" % "42.5.6")
  $endif$
}
