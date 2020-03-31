/*
1.	Wykorzystaj Pattern Matching w funkcji przyjmującej parametr typu String.
Dla stringów odpowiadających nazwom dni tygodnia funkcja
ma zwrócić „Praca” i „Weekend” (odpowiednio dla dni roboczych i wolnych),
dla pozostałych napisów „Nie ma takiego dnia”.

2.	Zdefiniuj klasę KontoBankowe z metodami wplata i wyplata oraz
własnością stanKonta - własność ma być tylko do odczytu.
Klasa powinna udostępniać konstruktor przyjmujący początkowy stan konta
oraz drugi, ustawiający początkowy stan konta na 0.

3.	Zdefiniuj klasę Osoba z własnościami imie i nazwisko. Stwórz kilka instancji tej klasy.
Zdefiniuj funkcję, która przyjmuje obiekt klasy osoba i przy pomocy Pattern Matching wybiera
i zwraca napis zawierający przywitanie danej osoby.
Zdefiniuj 2-3 różne przywitania dla konkretnych osób (z określonym imionami lub nazwiskami) oraz jedno domyślne.

4.	Zdefiniuj funkcję przyjmującą dwa parametry - wartość całkowitą i funkcję operującą na wartości całkowitej.
Zastosuj przekazaną jako parametr funkcję trzykrotnie do wartości całkowitej i zwróć wynik.
 */
object Main {
  def main(args: Array[String]): Unit = {

    val weekDays: List[String] = List("Poniedzialek", "Wtorek", "Sroda", "Czwartek", "Piatek", "Sobota", "Niedziela")

    //1
    println("1. PatternMatching: " + weekDays(0) + " - " + matchDay(weekDays(0)))
    println("1. PatternMatching: " + weekDays(6) + " - " + matchDay(weekDays(6))+"\n")

    //2
    val bankAccount = new BankAccount()
    val newBanckAccount = new BankAccount(123.45)

    println("2. Stan konta z poczatkowa suma: " + bankAccount.balance)
    println("2. Stan drugiego konta z domyslna suma: " + newBanckAccount.balance)

    //3
    val person1 = new Person("Kasia","Kasiowa")
    val person2 = new Person("Basia","Basiowa")
    val person3 = new Person("Marysia", "Marysiowa")
    val person4 = new Person("Adam", "Adamski")

    println(s"3. Say Hello to $person1: " + sayHello(person1))
    println(s"3. Say Hello to $person2: " + sayHello(person2))
    println(s"3. Say Hello to $person3: " + sayHello(person3))
    println(s"3. Say Hello to stranger: " + sayHello(person4)+"\n")

    //4
    var number = 10
    println(s"4. Add one three times to ${number} results in: " +  doTriple(calculate,number)+"\n")


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

  case class Person(val name: String, val surname: String)

  def sayHello(person: Main.Person): String ={
    person match {
      case Person(name,surname) if name == "Kasia" => s"Halo $name $surname!"
      case Person(name,surname) if name == "Basia" => s"Salut $name $surname!"
      case Person(name,surname) if name == "Marysia" => s"Hi $name $surname!"
      case default => "Hello Stranger!"
    }
  }

  def doTriple(f: Int => Int, n: Int) = f(f(f(n)))
  def calculate(n: Int) = n+1

}
