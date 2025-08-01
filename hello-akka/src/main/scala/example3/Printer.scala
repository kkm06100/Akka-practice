package example3

import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.Behaviors
import example3.ast.Messages.{PrintMessage, PrinterMessage}

object Printer {

  def apply(): Behavior[PrinterMessage] = {
    print()
  }

  private def print(): Behavior[PrinterMessage] =
    Behaviors.receive { (context, message) => message match {
      case PrintMessage(value) =>
        val parent = context.self.path.parent
        context.log.info("saver: {}, value: {}", parent.name, value)
        Behaviors.same
    }}
}
