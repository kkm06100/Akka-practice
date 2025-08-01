package example3

import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.Behaviors
import example3.ast.Messages.{DecreaseValue, IncreaseValue, PrintMessage, PrintValue, SaverMessage}

import java.util.UUID

object Saver {

  def apply(): Behavior[SaverMessage] = {
    save(0)
  }

  private def save(value: Int): Behavior[SaverMessage] = {
    Behaviors.setup(context => {
      val name = context.self.path.name;
      val printer = context.spawn(Printer(), name + "printer" + UUID.randomUUID())
      Behaviors.receiveMessage {
        case IncreaseValue() =>
          printer ! PrintMessage(value + 1)
          save(value + 1)
        case PrintValue() =>
          printer ! PrintMessage(value)
          Behaviors.same
        case DecreaseValue() =>
          printer ! PrintMessage(value - 1)
          save(value - 1)
      }
    })
  }
}
