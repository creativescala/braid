package braid

import braid.Habit
import braid.date.Date
import com.raquo.laminar.api.L.{_, given}
import org.scalajs.dom

import scala.scalajs.js

object Braid {
  def run(mount: dom.Element): Unit = {
    val model = Habit.habitsVar

    val controller = Controller(model)
    val view = View(controller)

    val app = view.view(model.signal)

    render(mount, app)
  }
}
