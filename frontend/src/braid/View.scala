package braid

import braid.date.Date
import braid.model.Habit
import com.raquo.laminar.api.L.{_, given}
import com.raquo.laminar.api.features.unitArrows
import com.raquo.laminar.nodes.ReactiveHtmlElement

import scala.scalajs.js

final case class Habit(id: Int, name: String, streak: Int, dates: Seq[Date])

class View(controller: Controller) {
  def last7Days: Seq[Date] = {
    val today = Date.today()
    for (i <- -6 to 0) yield {
      today.subtractDays(i)
    }
  }

  val daysOfTheWeek = Seq("Sun", "Mon", "Tue", "Wed", "Thur", "Fri", "Sat")

  def last7DaysTableHeadings: Seq[Element] =
    last7Days.zipWithIndex
      .map { (date, i) =>
        th(
          className := "min-w-16 px-3 py-4 text-center text-sm font-semibold text-gray-700",
          div(
            date.dayOfWeek.toShortString,
            div(
              className := "text-xs font-normal text-gray-500",
              date.day
            )
          )
        )
      }

  def view(habits: Signal[Map[Int, Habit]]): Div =
    div(
      className := "bg-white rounded-lg shadow-lg overflow-hidden",
      div(
        className := "overflow-x-auto",
        table(
          className := "w-full",
          thead(
            className := "bg-gray-50 border-b",
            tr(
              th(
                className := "px-6 py-4 text-left text-sm font-semibold text-gray-700",
                "Habit"
              ),
              th(
                className := "px-4 py-4 text-center text-sm font-semibold text-gray-700",
                "Streak"
              ),
              last7DaysTableHeadings,
              th(className := "px-4 py-4")
            )
          ),
          tbody(
            className := "divide-y divide-gray-200",
            children <-- habits.map(_.values.toSeq.map(habitView))
          )
        )
      )
    )

  def habitView(habit: Habit): Node =
    tr(
      className := "hover:bg-gray-50",
      td(className := "px-6 py-4 text-gray-800 font-medium", habit.name),
      td(
        className := "px-4 py-4 text-center",
        span(
          className := "inline-flex items-center px-3 py-1 rounded-full text-sm font-semibold bg-orange-100 text-orange-700",
          s"🔥 ${habit.streak}"
        )
      ),
      last7Days.map((date: Date) =>
        td(
          className := "px-3 py-4 text-center",
          habit.dates.find(d =>
            d.day == date.day &&
              d.month == date.month &&
              d.year == date.year
          ) match {
            case Some(_) =>
              div(
                onClick --> controller.toggleCompletionDate(habit.id, date),
                strong("Yes")
              )
            case None =>
              div(
                onClick --> controller.toggleCompletionDate(habit.id, date),
                "No"
              )
          }
        )
      ),
      td(
        className := "px-3 py-4 text-center",
        button(className := s"w-8 h-8 rounded-lg transition")
      ),
      td(
        className := "px-4 py-4 text-center",
        button(
          "Delete",
          onClick.flatMap(_ =>
            FetchStream.post("/habit/delete/" + habit.id)
          ) --> { responseText =>
            // println(responseText) || assume its a success response
            controller.dropSingleRow(habit.id)
          },
          className := "text-red-500 hover:text-red-700 transition"
        )
      )
    )
}
