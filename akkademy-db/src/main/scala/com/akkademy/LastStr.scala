package com.akkademy
import akka.actor.Actor
import akka.event.Logging
import scala.collection.mutable.HashMap
import com.akkademy.messages.SetRequest

class LastStr extends Actor{
  var receivedStr = ""
  val log = Logging(context.system, this)

  override def receive = {
    case msg: String => {
      log.info("received msg: {}", msg)
      receivedStr = msg
    }
    case _ => log.info("last msg:{}", receivedStr)
  }
}
