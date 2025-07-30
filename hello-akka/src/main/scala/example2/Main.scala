package example2

import akka.NotUsed
import akka.actor.typed.{ActorSystem, Behavior, Terminated}
import akka.actor.typed.scaladsl.Behaviors

object Main {
  def apply(): Behavior[NotUsed] =
    Behaviors.setup { context =>
      val chatRoom = context.spawn(ChatRoom(), "chatroom")
      val gabbler = context.spawn(Gabbler(), "gabbler")
      context.watch(gabbler)

      chatRoom ! ChatRoom.GetSession("ol' Gabbler", gabbler)

      Behaviors.receiveSignal {
        case (_, Terminated(_)) =>
          Behaviors.stopped
      }
    }

  def main(args: Array[String]): Unit = {
    ActorSystem(Main(), "ChatRoomDemo")
  }
}
