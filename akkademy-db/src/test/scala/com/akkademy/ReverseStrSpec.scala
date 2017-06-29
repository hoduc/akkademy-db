package com.akkademy
import akka.util.Timeout
import akka.actor.ActorSystem
import akka.actor.Props
import akka.pattern.ask
import scala.concurrent._
import scala.concurrent.duration._
import org.scalatest.{FunSpecLike, Matchers}

class ReverseStrSpec extends FunSpecLike with Matchers {
  val system = ActorSystem()
  implicit val timeout = Timeout(5 seconds)
  val rStrActor = system.actorOf(Props(classOf[ReverseStrActor]))

  def askReverStrActor(message: Object): Future[String] = (rStrActor ? message).mapTo[String]

  describe("ReverseStrActor sync") {
    it("should be cba"){
      val future = rStrActor ? "abc"
      val result = Await.result(future.mapTo[String], 1 second)
      assert(result == "cba")
    }

    it("should fail on not string") {
      val future = rStrActor ? new Integer(123)
      intercept[Exception] {
        Await.result(future.mapTo[String], 1 second)
      }
    }
  }
}
