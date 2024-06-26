package $package$.$name;format="word"$

import io.gatling.core.Predef._
$if(amqpPlugin.truthy)$
import org.galaxio.gatling.amqp.Predef._
$endif$
import org.galaxio.gatling.config.SimulationConfig._
import org.galaxio.gatling.influxdb.Annotations
$if(kafkaPlugin.truthy)$
import org.galaxio.gatling.kafka.Predef._
$endif$
$if(jdbcPlugin.truthy)$
import org.galaxio.gatling.jdbc.Predef._
$endif$
import $package$.$name;format="word"$.scenarios._

class MaxPerformance extends Simulation with Annotations {

  setUp(
    $if(http.truthy)$
    HttpScenario().inject(
      // интенсивность на ступень
      incrementUsersPerSec((intensity / stagesNumber).toInt)
        // Количество ступеней
        .times(stagesNumber)
        // Длительность полки
        .eachLevelLasting(stageDuration)
        // Длительность разгона
        .separatedByRampsLasting(rampDuration)
        // Начало нагрузки с
        .startingFrom(0),
    ),
    $endif$
    $if(jdbcPlugin.truthy)$
    JdbcScenario().inject(
      // интенсивность на ступень
      incrementUsersPerSec((intensity / stagesNumber).toInt)
        // Количество ступеней
        .times(stagesNumber)
        // Длительность полки
        .eachLevelLasting(stageDuration)
        // Длительность разгона
        .separatedByRampsLasting(rampDuration)
        // Начало нагрузки с
        .startingFrom(0),
    ),
    $endif$
    $if(amqpPlugin.truthy)$
    AmqpScenario().inject(
      // интенсивность на ступень
      incrementUsersPerSec((intensity / stagesNumber).toInt)
        // Количество ступеней
        .times(stagesNumber)
        // Длительность полки
        .eachLevelLasting(stageDuration)
        // Длительность разгона
        .separatedByRampsLasting(rampDuration)
        // Начало нагрузки с
        .startingFrom(0),
    ),
    $endif$
    $if(kafkaPlugin.truthy)$
    KafkaScenario().inject(
      // интенсивность на ступень
      incrementUsersPerSec((intensity / stagesNumber).toInt)
        // Количество ступеней
        .times(stagesNumber)
        // Длительность полки
        .eachLevelLasting(stageDuration)
        // Длительность разгона
        .separatedByRampsLasting(rampDuration)
        // Начало нагрузки с
        .startingFrom(0),
    ),
    $endif$
  ).protocols(
    $if(http.truthy)$httpProtocol,$endif$
    $if(jdbcPlugin.truthy)$jdbcProtocol,$endif$
    $if(amqpPlugin.truthy)$amqpProtocol,$endif$
    $if(kafkaPlugin.truthy)$kafkaProtocol,$endif$
    // общая длительность теста
  ).maxDuration(testDuration)

}
