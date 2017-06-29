package com.akkademy
import akka.util.Timeout
import scala.concurrent._
import scala.concurrent.duration._
import org.scalatest.{FunSpecLike, Matchers}

class SClientIntegrationSpec extends FunSpecLike with Matchers {
  val client = new SClient("127.0.0.1:2552")

  describe("akkademyDbClient") {
    it("should set a value") {
      client.set("123", new Integer(123))
      val futureResult = client.get("123")
      val result = Await.result(futureResult, 10 seconds)
      result should equal(123)
    }
  }
}
