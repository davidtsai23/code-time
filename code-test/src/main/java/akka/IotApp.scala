package akka

import akka.actor.ActorSystem

object IotApp {

  def main(args: Array[String]): Unit = {
    val system = ActorSystem("iot-system")

    val supervisor = system.actorOf(IotSupervisor.props(),"iot-supervisor")
    system.terminate()
  }
}
