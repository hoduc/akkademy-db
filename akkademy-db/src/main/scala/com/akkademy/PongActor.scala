package com.akkademy
import akka.actor.Actor
import akka.actor.Props
import akka.actor.Status
import akka.event.Logging

class PongActor extends Actor{
  override def receive: Receive = {
    case "Ping" => sender() ! "Pong"
    case _ => sender() ! Status.Failure(new Exception("unknown message"))
  }
}

object PongActor{
  def props(response: String): Props = {
    Props(classOf[PongActor], response)
  }
}
