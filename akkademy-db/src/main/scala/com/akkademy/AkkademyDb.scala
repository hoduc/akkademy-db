package com.akkademy
import akka.actor.Actor
import akka.actor.Status
import akka.actor.ActorSystem
import akka.actor.Props
import akka.event.Logging
import scala.Option
import scala.Some
import scala.None
import scala.collection.mutable.HashMap
import com.akkademy.messages.SetRequest
import com.akkademy.messages.GetRequest
import com.akkademy.messages.KeyNotFoundException

class AkkademyDb extends Actor{
  val map = new HashMap[String, Object]
  val log = Logging(context.system, this)

  override def receive = {
    case SetRequest(key, value) => 
      log.info("received SetRequest - key: {} value: {}", key, value)
      map.put(key, value)
      sender() ! Status.Success
    
    case GetRequest(key) =>
      log.info("received GetRequest - key: {}", key)
      val response = map.get(key)
      response match{
        case Some(x) => sender() ! x
        case None => sender() ! Status.Failure(new KeyNotFoundException(key))
      }
      
    case o => Status.Failure(new ClassNotFoundException)
  }
}

object Main extends App {
  val system = ActorSystem("akkademy")
  system.actorOf(Props[AkkademyDb], name = "akkademy-db")
}
