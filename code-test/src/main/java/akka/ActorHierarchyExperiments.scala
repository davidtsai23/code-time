package akka

import akka.actor.{Actor, ActorSystem, Props}

import scala.io.StdIn

object ActorHierarchyExperiments extends App {
    val system = ActorSystem("test")
    val firstRef = system.actorOf(PrintMyActorRef.props,"first-actor")
    println(s"First: $firstRef")
    firstRef ! "print-def"
    system.terminate()
}

object PrintMyActorRef{
  def props:Props = Props(new PrintMyActorRef)

}

class PrintMyActorRef extends Actor{
   override def receive: Receive = {
     case "print-def" =>
       val secondRef = context.actorOf(PrintMyActorRef.props,"second-actor")
       println(s"Second: $secondRef")
       secondRef ! "finish"
     case "finish" =>
       println("finish")
   }
}