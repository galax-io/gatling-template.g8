package $package$.$name; format = "word" $

import io.gatling.app.Gatling
import io.gatling.core.config.GatlingPropertiesBuilder

object GatlingRunner {

  def main(args: Array[String]): Unit = {

    // this is where you specify the class you want to run
    val simulationClass = classOf[Debug].getName

    Gatling.main(
      args ++
        Array(
          GatlingCliOptions.Simulation.shortOption,
          simulationClass,
          GatlingCliOptions.ResultsFolder.shortOption,
          "results",
          GatlingCliOptions.Launcher.shortOption,
          "sbt",
        ),
    )
  }

}
