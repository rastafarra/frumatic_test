package actions

trait Response

case class Amount(summ : Int) extends Response
case object NotEnoughMoney extends Response
case object NotFound extends Response
