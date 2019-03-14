package com.jfs.akka

import akka.actor.Actor
import akka.event.Logging

class MyActor extends Actor{

  val log = Logging(context.system,this)

  override def receive = {
    case "test" => log.info("receive test")
    case _ => log.info("receive other message")
  }
}
