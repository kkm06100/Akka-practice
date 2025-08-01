package example3.ast

import akka.actor.typed.ActorRef

object Messages {

  sealed trait SaverMessage
  final case class IncreaseValue() extends SaverMessage
  final case class PrintValue() extends SaverMessage
  final case class DecreaseValue() extends SaverMessage

  sealed trait PrinterMessage
  final case class PrintMessage(value: Int) extends PrinterMessage

  sealed trait GuardianMessage
  final case class Increase(saver: ActorRef[SaverMessage]) extends GuardianMessage
  final case class Decrease(saver: ActorRef[SaverMessage]) extends GuardianMessage
  final case class Print(saver: ActorRef[SaverMessage]) extends  GuardianMessage
}
