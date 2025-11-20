package braid

import braid.Habit
import braid.date.Date
import com.raquo.laminar.api.L.{_, given}
import org.scalajs.dom

import scala.scalajs.js

object Braid {
  def run(mount: dom.Element): Unit = {
    val model =
      Var(
        Map(
          1 -> Habit(1, "Work on Braid", 0, Seq()),
          2 -> Habit(2, "Exercise", 5, Seq()),
          3 -> Habit(3, "Read", 3, Seq(Date.today()))
        )
      )

    val controller = Controller(model)
    val view = View(controller)

    val app = view.view(model.signal)

    render(mount, app)
  }
}
