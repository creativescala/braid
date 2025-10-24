package braid.date

/** Represents a (Gregorian) calendar date (day, month, and year) without a time
  * zone.
  */
trait LocalDate {
  def day: Int
  def dayOfWeek: Day
  def month: Month
  def year: Int
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

  def toShortString: String =
    this match {
      case Monday    => "Mon"
      case Tuesday   => "Tue"
      case Wednesday => "Wed"
      case Thursday  => "Thu"
      case Friday    => "Fri"
      case Saturday  => "Sat"
      case Sunday    => "Sun"
    }
}
