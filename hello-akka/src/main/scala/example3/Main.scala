package example3

import akka.NotUsed
import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.{ActorSystem, Behavior}
import example3.ast.Messages.{Decrease, Increase, Print}

/**
 * <h1>부족한점</h1>
 * <ol>
 *   <li>
 *    출력할 때 마다 printer 를 생성함 심지어 Behaviors.stopped 도 안함
 *   </li>
 *   <li>
 *     변경하게 된다면 routerPool 사용으로 병렬 작업 필요
 * </ol>
 *
 */
object Main {

  def apply(): Behavior[NotUsed] = Behaviors.setup(context => {
    val saver1 = context.spawn(Saver(), "saver1")
    val saver2 = context.spawn(Saver(), "saver2")

    val guardian = context.spawn(Guardian(), "guardian")

    guardian ! Increase(saver1)
    guardian ! Increase(saver1)
    guardian ! Decrease(saver2)
    guardian ! Print(saver1)
    guardian ! Print(saver2)

    Behaviors.same
  })

  def main(args: Array[String]): Unit = {

    val system: ActorSystem[NotUsed] =
      ActorSystem(Main(), "main")
  }
}
