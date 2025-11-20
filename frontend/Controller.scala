package braid

import braid.Habit
import braid.date.Date
import com.raquo.laminar.api.L.{_, given}

class Controller(model: Var[Map[Int, Habit]]) {
  def toggleCompletionDate(id: Int, date: Date): Unit =
    model.update(habits =>
      habits.get(id) match {
        case Some(habit) =>
          habits + (id -> habit.copy(dates =
            if habit.dates.contains(date) then habit.dates.filterNot(_ == date)
            else date +: habit.dates
          ))
        case None => habits
      }
    )
}
