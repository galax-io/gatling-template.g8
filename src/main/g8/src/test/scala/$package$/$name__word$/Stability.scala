package $package$.$name;format="word"$

import io.gatling.core.Predef._
$if(amqpPlugin.truthy)$
import io.cosmospf.gatling.amqp.Predef._
$endif$
import io.cosmospf.gatling.config.SimulationConfig._
import io.cosmospf.gatling.influxdb.Annotations
$if(kafkaPlugin.truthy)$
import io.cosmospf.gatling.kafka.Predef._
$endif$
$if(jdbcPlugin.truthy)$
import io.cosmospf.load.jdbc.Predef._
$endif$
import $package$.$name;format="word"$.scenarios._

class Stability extends Simulation with Annotations {

  setUp(
    $if(http.truthy)$
    HttpScenario().inject(
      // разгон
      rampUsersPerSec(0) to intensity.toInt during rampDuration,
      // полка
      constantUsersPerSec(intensity.toInt) during stageDuration,
    ),
    $endif$
    $if(jdbcPlugin.truthy)$
    JdbcScenario().inject(
      // разгон
      rampUsersPerSec(0) to intensity.toInt during rampDuration,
      // полка
      constantUsersPerSec(intensity.toInt) during stageDuration,
    ),
    $endif$
    $if(amqpPlugin.truthy)$
    AmqpScenario().inject(
      // разгон
      rampUsersPerSec(0) to intensity.toInt during rampDuration,
      // полка
      constantUsersPerSec(intensity.toInt) during stageDuration,
    ),
    $endif$
    $if(kafkaPlugin.truthy)$
    KafkaScenario().inject(
      // разгон
      rampUsersPerSec(0) to intensity.toInt during rampDuration,
      // полка
      constantUsersPerSec(intensity.toInt) during stageDuration,
    ),
    $endif$
  ).protocols(
    $if(http.truthy)$httpProtocol,$endif$
    $if(jdbcPlugin.truthy)$jdbcProtocol,$endif$
    $if(amqpPlugin.truthy)$amqpProtocol,$endif$
    $if(kafkaPlugin.truthy)$kafkaProtocol,$endif$
    // длительность теста = разгон + полка
  ).maxDuration(testDuration)

}
