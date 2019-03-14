package com.jfs.akka.lifecycle

import akka.actor.{Actor, Props}

object SupervisorActor{
  def props:Props = Props(new SupervisorActor)
}

class SupervisorActor extends Actor{

  val child = context.actorOf(SupervisedActor.props,"child")
  override def receive: Receive = {
    case "failChild" ⇒ child ! "fail"
  }

}

object SupervisedActor {
  def props: Props =
    Props(new SupervisedActor)
}


class SupervisedActor extends Actor{


  override def preStart(): Unit = println("supervised actor started")
  override def postStop(): Unit = println("supervised actor stopped")

  override def receive: Receive = {
    case "fail" =>
      println("supervised actor fails now")
      throw new Exception("I failed!")
  }
}
