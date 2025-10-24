package braid.date

/** Represents a (Gregorian) calendar date (day, month, and year) without a time
  * zone.
  */
trait LocalDate {
  def day: Int
  def dayOfWeek: Day
  def month: Month
  def year: Int

  def yesterday: LocalDate
  def tomorrow: LocalDate
  def addDays(days: Int): LocalDate
  def subtractDays(days: Int): LocalDate
}

enum Month {
  case January
  case February
  case March
  case April
  case May
  case June
  case July
  case August
  case September
  case October
  case November
  case December
}

enum Day {
  case Monday
  case Tuesday
  case Wednesday
  case Thursday
  case Friday
  case Saturday
  case Sunday
}
