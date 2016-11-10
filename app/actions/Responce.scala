package actions

trait Responce

case class Amount(summ : Int) extends Responce
case object NotEnoughMoney extends Responce
case object NotFound extends Responce
