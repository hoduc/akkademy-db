package com.akkademy
import akka.util.Timeout
import akka.actor.ActorSystem
import akka.actor.Props
import akka.pattern.ask
import scala.concurrent._
import scala.concurrent.duration._
import org.scalatest.{FunSpecLike, Matchers}

class AskExampleSpec extends FunSpecLike with Matchers{
  val system = ActorSystem()
  implicit val timeout = Timeout( 5 seconds )
  val pongActor = system.actorOf(Props(classOf[PongActor])) //get back actorRef


  def askPong(message: String): Future[String] = (pongActor ? message).mapTo[String]

  describe("Pong actor"){
    it("should respond with Pong"){
      val future = pongActor ? "Ping"
      val result = Await.result(future.mapTo[String], 1 second)
      assert(result == "Pong")
    }

    it("should fail on unknown message"){
      val future = pongActor ? "unknown"
      intercept[Exception]{
        Await.result(future.mapTo[String], 1 second)
      }
    }
  }

  describe("FutureExamples"){
    import scala.concurrent.ExecutionContext.Implicits.global
    it("should print to console"){
      askPong("Ping").onSuccess({
        case x: String => println("replied with: " + x)
      })
      Thread.sleep(100)
    }

    it("should print Got exception"){
      askPong("hello").onFailure({
        case e: Exception => println("Got exception")
      })
      Thread.sleep(100)
    }
  }
}
