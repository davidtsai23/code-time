package akka.device

import akka.actor.{Actor, ActorLogging, ActorRef, ActorSystem, Props}
import akka.device.Device.{ReadTemperature, RecordTemperature, RespondTemperature, TemperatureRecorded}

object Device{
  def props(groupId:String,deviceId:String):Props = Props(new Device(groupId,deviceId))
  final case class ReadTemperature(requestId: Long)
  final case class RespondTemperature(requestId: Long, value: Option[Double])

  final case class RecordTemperature(requestId: Long, value: Double)
  final case class TemperatureRecorded(requestId: Long)
}

class Device (groupId: String, deviceId: String) extends Actor with ActorLogging{

  var lastTemperatureReading: Option[Double] = None

  override def receive: Receive = {
    case RecordTemperature(id, value) ⇒
      log.info("Recorded temperature reading {} with {}", value, id)
      lastTemperatureReading = Some(value)
      sender() ! TemperatureRecorded(id)

    case ReadTemperature(id) =>
      sender() ! RespondTemperature(id,lastTemperatureReading)
  }

  override def preStart(): Unit = log.info("Device actor {}-{} started", groupId, deviceId)

  override def postStop(): Unit = log.info("Device actor {}-{} stopped", groupId, deviceId)
}

object DeviceTest extends App{
  val system:ActorSystem = ActorSystem("device")
  val deviceActor:ActorRef = system.actorOf(Device.props("group","device"))

  deviceActor.tell(Device.RecordTemperature(1L,55.0),deviceActor)
  deviceActor.tell(Device.ReadTemperature(2),deviceActor)

}
