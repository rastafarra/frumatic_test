package controllers

import org.scalatestplus.play._
import play.api.test._
import play.api.test.Helpers._

class WalletTest extends PlaySpec with OneAppPerSuite {

  val acc1 = "111"
  val acc2 = "222"

  "Index" should {
    "not implemented" in {
      status (route (app, FakeRequest(GET, "/")).get) mustBe NOT_IMPLEMENTED
    }
  }

  "Amount" should {
    "not found by defaul" in {
      contentAsString(route(app, FakeRequest(GET, s"/accounts/$acc1")).get) mustBe "NotFound"
      contentAsString(route(app, FakeRequest(GET, s"/accounts/$acc2")).get) mustBe "NotFound"
    }
  }

  "Add" should {
    "added value" in {
      contentAsString(route(app, FakeRequest(POST, s"/add?account=$acc1&summ=100")).get) mustBe "Amount(100)"
      contentAsString(route(app, FakeRequest(POST, s"/add?account=$acc2&summ=100")).get) mustBe "Amount(100)"
    }
  }

  "Move ok" should {
    "move" in {
      contentAsString(route(app, FakeRequest(POST, s"/move?from=$acc1&to=$acc2&summ=50")).get) mustBe "Amount"
    }
  }

  "Move with NotEnoughMoney" should {
    "move error" in {
      contentAsString(route(app, FakeRequest(POST, s"/move?from=$acc1&to=$acc2&summ=150")).get) mustBe "NotEnoughMoney"
    }
  }

  "Amount" should {
    "get answer" in {
      contentAsString(route(app, FakeRequest(GET, s"/accounts/$acc1")).get) mustBe "Amount(50)"
      contentAsString(route(app, FakeRequest(GET, s"/accounts/$acc2")).get) mustBe "Amount(150)"
    }
  }
}
