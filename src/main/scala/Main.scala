/*
1.	Wykorzystaj Pattern Matching w funkcji przyjmującej parametr typu String.
Dla stringów odpowiadających nazwom dni tygodnia funkcja
ma zwrócić „Praca” i „Weekend” (odpowiednio dla dni roboczych i wolnych),
dla pozostałych napisów „Nie ma takiego dnia”.

2.	Zdefiniuj klasę KontoBankowe z metodami wplata i wyplata oraz
własnością stanKonta - własność ma być tylko do odczytu.
Klasa powinna udostępniać konstruktor przyjmujący początkowy stan konta
oraz drugi, ustawiający początkowy stan konta na 0.
 */
object Main {
  def main(args: Array[String]): Unit = {

    val weekDays: List[String] = List("Poniedzialek", "Wtorek", "Sroda", "Czwartek", "Piatek", "Sobota", "Niedziela")

    println("1. PatternMatching: " + weekDays(0) + " - " + matchDay(weekDays(0)))
    println("1. PatternMatching: " + weekDays(6) + " - " + matchDay(weekDays(6))+"\n")

    val bankAccount = new BankAccount()
    val newBanckAccount = new BankAccount(123.45)

    println("2. Stan konta z poczatkowa suma: " + bankAccount.balance)
    println("2. Stan drugiego konta z domyslna suma: " + newBanckAccount.balance)


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

  class BankAccount(val balance: Double){

    def this() = this(balance = 0)

    def withdraw(){
      System.out.println("Wyplata pieniedzy")
    }

    def transfer(){
      System.out.println("Wplata pieniedzy")
    }
  }

}
