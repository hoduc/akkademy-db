package com.akkademy
import akka.actor.Actor
import akka.actor.Status
import akka.event.Logging

class ReverseStrActor extends Actor{
  val log = Logging(context.system, this)

  override def receive = {
    case msg:String =>
      log.info("received str:{}", msg)
      sender() ! msg.reverse
    case _ => sender() ! Status.Failure(new Exception("unknown type"))
  }
}
