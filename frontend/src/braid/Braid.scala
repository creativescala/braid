package braid

import braid.Habit
import braid.date.Date
import com.raquo.laminar.api.L.{_, given}
import org.scalajs.dom

import scala.scalajs.js

object Braid {
  def run(mount: dom.Element): Unit = {
    val model =
      Seq(
        Habit(1, "Work on Braid", 0, Seq()),
        Habit(2, "Exercise", 5, Seq()),
        Habit(3, "Read", 3, Seq(Date.today()))
      )

    val app = View.view(model)

    render(mount, app)
  }
}
