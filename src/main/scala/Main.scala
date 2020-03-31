/*
1.	Wykorzystaj Pattern Matching w funkcji przyjmującej parametr typu String.
Dla stringów odpowiadających nazwom dni tygodnia funkcja
ma zwrócić „Praca” i „Weekend” (odpowiednio dla dni roboczych i wolnych),
dla pozostałych napisów „Nie ma takiego dnia”.
 */
object Main {
  def main(args: Array[String]): Unit = {

    val weekDays: List[String] = List("Poniedzialek", "Wtorek", "Sroda", "Czwartek", "Piatek", "Sobota", "Niedziela")

    println("1. PatternMatching: " + weekDays(0) + " - " + matchDay(weekDays(0)))
    println("1. PatternMatching: " + weekDays(6) + " - " + matchDay(weekDays(6)))

  }

  def matchDay(day: String): String = {
    day match {
      case "Poniedzialek" => "Praca"
      case "Wtorek" => "Praca"
      case "Sroda" => "Praca"
      case "Czwartek" => "Praca"
      case "Piatek" => "Praca"
      case "Sobota" => "Weekened"
      case "Niedziela" => "Weekend"
    }
  }

}
