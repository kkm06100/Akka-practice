package example3

import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.Behaviors
import example3.ast.Messages.{Decrease, DecreaseValue, GuardianMessage, Increase, IncreaseValue, Print, PrintValue}

object Guardian {

  def apply(): Behavior[GuardianMessage] =
    route()

  private def route(): Behavior[GuardianMessage] = {
    Behaviors.receiveMessage {
      case Increase(saver) =>
        saver ! IncreaseValue()
        Behaviors.same
      case Decrease(saver) =>
        saver ! DecreaseValue()
        Behaviors.same
      case Print(saver) =>
        saver ! PrintValue()
        Behaviors.same
    }
  }
}
