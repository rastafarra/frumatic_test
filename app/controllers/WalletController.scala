package controllers

import javax.inject.Inject

import play.api.libs.concurrent.Execution.Implicits.defaultContext

import scala.concurrent.duration._
import actions._
import actors.Wallet
import akka.actor.ActorSystem
import akka.pattern.ask
import akka.util.Timeout
import play.api.mvc.{Action, Controller}
import views.html.defaultpages.badRequest



class WalletController @Inject()(system: ActorSystem) extends Controller {

  val wallet = system.actorOf(Wallet.props, "wallet-actor")
  implicit val timeout: Timeout = 5 seconds



  def index = TODO



  def amount (account : String) = Action.async {
    (wallet ? GetAmount (account)).map(resp => resp match {
      case Amount (i) => Ok (resp)
      case _ => BadRequest (resp.toString)
    })
  }



  def add (account : String, summ : Int) = Action.async {
    (wallet ? Add (account, summ)).map(resp => Ok (resp))
  }



  def move (from : String, to : String, summ : Int) = Action.async {
    (wallet ? Move (from, to, summ)).map(resp => resp match {
      case Amount => Ok (resp)
      case NotEnoughMoney => BadRequest (resp.toString)
    })
  }
}
