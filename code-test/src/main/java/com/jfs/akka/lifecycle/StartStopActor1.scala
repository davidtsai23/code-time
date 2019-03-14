package com.jfs.akka.lifecycle

import akka.actor.{Actor, Props}

class StartStopActor1 extends Actor {


  override def preStart(): Unit = {
    println("first started")
    context.actorOf(StartStopActor2.props,"second")
  }

  override def postStop(): Unit = println("first stopped")

  override def receive: Receive = {
    case "stop" ⇒ context.stop(self)
  }
}

object StartStopActor1{
  def props:Props = Props(new StartStopActor1)
}

class StartStopActor2 extends Actor{
  override def preStart(): Unit = println("second started")
  override def postStop(): Unit = println("second stopped")
  override def receive: Receive = {
      Actor.emptyBehavior
  }
}

object StartStopActor2{
  def props:Props = Props(new StartStopActor2)
}
