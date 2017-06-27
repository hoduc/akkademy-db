package com.akkademy
import akka.util.Timeout
import org.scalatest.{BeforeAndAfterEach, FunSpecLike, Matchers}
import akka.actor.ActorSystem
import com.akkademy.messages.SetRequest
import akka.testkit.TestActorRef


class LastStrSpec extends FunSpecLike with Matchers with BeforeAndAfterEach {
  implicit val system = ActorSystem()
  describe("laststr") {
    describe("given hello"){
      it("should store receivedmsg into laststr val"){
        val actorRef = TestActorRef(new LastStr)
        actorRef ! "hello"
        val lastStr = actorRef.underlyingActor
        lastStr.receivedStr should equal("hello")
      }
    }

    describe("given hello goodbye"){
      it("should store goodbye as last msg"){
        val actorRef = TestActorRef(new LastStr)
        actorRef ! "hello"
        actorRef ! "goodbye"
        val lastStr = actorRef.underlyingActor
        lastStr.receivedStr should equal("goodbye")
      }
    }

    describe("give hello SetRequest"){
      it("should store hello last"){
        val actorRef = TestActorRef(new LastStr)
        actorRef ! "hello"
        actorRef ! SetRequest("msg","goodbye")
        val lastStr = actorRef.underlyingActor
        lastStr.receivedStr should equal("hello")
      }
    }
  }
}
