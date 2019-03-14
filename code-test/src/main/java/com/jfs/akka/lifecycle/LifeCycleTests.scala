package com.jfs.akka.lifecycle

import akka.actor.ActorSystem

object LifeCycleTests extends App {

  //val first = ActorSystem("test").actorOf(StartStopActor1.props)
  //first ! "stop"

  val superviseActor = ActorSystem("supervise").actorOf(SupervisorActor.props)
  superviseActor ! "failChild"
}
