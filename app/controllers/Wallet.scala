package controllers

import javax.inject.Inject

import play.api.libs.concurrent.Execution.Implicits.defaultContext

import scala.concurrent.duration._
import actions.{Add, GetAmount, Get, Move}
import actors.Wallet
import akka.actor.ActorSystem
import akka.pattern.ask
import akka.util.Timeout
import play.api.mvc.{Action, Controller}



class Wallet @Inject()(system: ActorSystem) extends Controller {

  val wallet = system.actorOf(Wallet.props, "wallet-actor")
  implicit val timeout: Timeout = 5 seconds



  def index = TODO



  def amount (account : String) = Action.async {
    (wallet ? GetAmount (account)).map(resp => Ok (s"$resp"))
  }



  def add (account : String, summ : Int) = Action.async {
    (wallet ? Add (account, summ)).map(resp => Ok (s"$resp"))
  }



  def move (from : String, to : String, summ : Int) = Action.async {
    (wallet ? Move (from, to, summ)).map(resp => Ok (s"$resp"))
  }
}
