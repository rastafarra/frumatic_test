package actions

trait Request

case class GetAmount(account : String) extends Request

case class Get (account : String, summ : Int) extends Request

case class Add (account : String, summ : Int) extends Request

case class Move (from : String, to : String, summ : Int) extends Request
