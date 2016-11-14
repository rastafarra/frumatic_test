package controllers

import org.scalatestplus.play._
import play.api.test._
import play.api.test.Helpers._

class WalletTest extends PlaySpec with OneAppPerSuite {

  val acc1 = "111"
  val acc2 = "222"


  "Index" should {
    "should not be implemented" in {
      status (route (app, FakeRequest(GET, "/")).get) mustBe NOT_IMPLEMENTED
    }
  }

  "Amount" should {
    "should not be found by default" in {
      contentAsString(route(app, FakeRequest(GET, s"/accounts/$acc1")).get) mustBe "NotFound"
      contentAsString(route(app, FakeRequest(GET, s"/accounts/$acc2")).get) mustBe "NotFound"
    }
  }

  "Add" should {
    "should add the value and respond with ok" in {
      contentAsString(route(app, FakeRequest(POST, s"/add?account=$acc1&summ=100")).get) mustBe "Amount(100)"
      contentAsString(route(app, FakeRequest(POST, s"/add?account=$acc2&summ=100")).get) mustBe "Amount(100)"
    }
  }

  "Move ok" should {
    "should move value to another account and respond with ok" in {
      contentAsString(route(app, FakeRequest(POST, s"/move?from=$acc1&to=$acc2&summ=50")).get) mustBe "Amount"
    }
  }

  "Move with not enogh money" should {
    "should respond with  error NotEnoughMoney" in {
      contentAsString(route(app, FakeRequest(POST, s"/move?from=$acc1&to=$acc2&summ=150")).get) mustBe "NotEnoughMoney"
    }
  }

  "Amount" should {
    "should return proper amount" in {
      contentAsString(route(app, FakeRequest(GET, s"/accounts/$acc1")).get) mustBe "Amount(50)"
      contentAsString(route(app, FakeRequest(GET, s"/accounts/$acc2")).get) mustBe "Amount(150)"
    }
  }
}
