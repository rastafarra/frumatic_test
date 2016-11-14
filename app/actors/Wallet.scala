package actors

import actions._
import akka.actor.{Actor, Props}
import akka.actor.Actor.Receive
import play.api.Logger

object Wallet {
  def props = Props [Wallet]
}

class Wallet extends Actor {
  val wlt = scala.collection.mutable.Map [String, Int] ()



  override def receive: Receive = {
    case GetAmount (account) => sender ! getAmountHandler (account)
    case Add (account, summ) => sender ! addHandler(account, summ)
    case Get (account, summ) => sender ! getHandler(account, summ)
    case Move (from, to, summ) => sender ! moveHandler(from, to, summ)
  }



  def getAmountHandler(account: String) = {
    wlt.get(account) match {
      case Some(summ) => Amount(summ)
      case None => NotFound
    }
  }



  def addHandler (account : String, summ : Int) = {
    val amount = wlt.get (account) match {
      case Some (s) => s + summ
      case None => summ
    }

    wlt.put (account, summ)

    Amount (summ)
  }



  def getHandler (account : String, summ : Int) = {
    wlt.get (account) match {
      case Some (s) => summ > s match {
          case true => NotFound
          case false => {
            wlt.put(account, s - summ)
            Amount (s - summ)
          }
        }
      case None => NotFound
    }
  }




  def moveHandler(from: String, to: String, summ: Int) = {
    (wlt.get(from), wlt.get(to)) match {
      case (Some(sFrom), Some(sTo)) => sFrom < summ match {
          case true => NotEnoughMoney
          case false => {
            wlt.put(from, sFrom - summ)
            wlt.put(to, sTo + summ)

            Amount
          }
        }
      case (_, _) => NotFound
    }
  }
}
