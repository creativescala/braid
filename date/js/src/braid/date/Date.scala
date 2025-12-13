package braid.date

import scala.scalajs.js

final case class Date(
    val day: Int,
    val dayOfWeek: Day,
    val month: Month,
    val year: Int
) extends LocalDate {
  def toJsDate: js.Date = {
    val jsDate = new js.Date()

    jsDate.setDate(day)
    jsDate.setMonth(Date.monthArray.indexOf(month))
    jsDate.setFullYear(year)

    jsDate
  }

  def yesterday: Date = {
    subtractDays(1)
  }

  def tomorrow: Date = {
    addDays(1)
  }

  def addDays(days: Int): Date = {
    val jsDate = this.toJsDate
    jsDate.setDate(jsDate.getDate() + days)
    Date.fromJsDate(jsDate)
  }

  def subtractDays(days: Int): Date = {
    addDays(-days)
  }
}
object Date {
  // Days of week arranged so the index into the array is the value returned by the JS Date.getDay method
  private[date] val dayOfWeekArray: Array[Day] = Array(
    Day.Sunday,
    Day.Monday,
    Day.Tuesday,
    Day.Wednesday,
    Day.Thursday,
    Day.Friday,
    Day.Saturday
  )
  // Months arranged so the index into the array is the value returned by the JS Date.getMonth method
  private[date] val monthArray: Array[Month] = Array(
    Month.January,
    Month.February,
    Month.March,
    Month.April,
    Month.May,
    Month.June,
    Month.July,
    Month.August,
    Month.September,
    Month.October,
    Month.November,
    Month.December
  )

  def fromJsDate(date: js.Date): Date = {
    val day = date.getDate().toInt
    val dayOfWeek = dayOfWeekArray(date.getDay().toInt)
    val month = monthArray(date.getMonth().toInt)
    val year = date.getFullYear().toInt

    Date(day, dayOfWeek, month, year)
  }

  def today(): Date = {
    fromJsDate(new js.Date(js.Date.now()))
  }

}
