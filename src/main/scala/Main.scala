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

5.	Zdefiniuj klasę Osoba i trzy traity: Student, Nauczyciel, Pracownik. Osoba powinna mieć własności
read only: imie, nazwisko, podatek. Pracownik powinien mieć własność pensja (z getterem i seterem).
Student i Pracownik powinni przesłaniać własność podatek – dla Studenta zwracamy 0, dla Pracownika 20% pensji.
Nauczyciel powinien dziedziczyć z Pracownika, dla niego podatek zwraca 10% pensji. Stwórz obiekty z każdym z traitów,
pokaż jak podatek działa dla każdego z nich. Stwórz obiekty z traitami Student i Pracownik,
pokaż jak podatek zadziała w zależności od kolejności w jakiej te traity zostały dodane przy tworzeniu obiektu.
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

    bankAccount.transfer(100.0)
    newBanckAccount.withdraw(10.0)

    println("2. Stan konta z poczatkowa suma po wplacie: " + bankAccount.balance)
    println("2. Stan drugiego konta z domyslna suma po wyplacie: " + newBanckAccount.balance + "\n")

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

		//5
		val pracownik1 = new Osoba("Bartlomiej","Janusz") with Pracownik
		println(s"Pensja pracownika: ${pracownik1.imie} ${pracownik1.nazwisko} (domyslna) wynosi ${pracownik1.get_pensja}, a podatek ${pracownik1.get_podatek}")

		//read only: imie, nazwisko, podatek
		//pracownik.imie = "X"
		//pracownik.nazwisko = "Y"
		//pracownik.podatek = 0

		pracownik1.set_pensja(1000)
		println(s"Pensja pracownika: ${pracownik1.imie} ${pracownik1.nazwisko} po podwyzce wynosi ${pracownik1.get_pensja}, a podatek ${pracownik1.get_podatek}"+"\n")

		val nauczyciel1 = new Osoba("Karol", "Karolkiewicz") with Nauczyciel
		println(s"Pensja nauczyciela: ${nauczyciel1.imie} ${nauczyciel1.nazwisko} (domyslna) wynosi ${nauczyciel1.get_pensja}, a podatek ${nauczyciel1.get_podatek}")
		nauczyciel1.set_pensja(1000)
		println(s"Pensja nauczyciela: ${nauczyciel1.imie} ${nauczyciel1.nazwisko} po podwyzce wynosi ${nauczyciel1.get_pensja}, a podatek ${nauczyciel1.get_podatek}" + "\n")

		val student1 = new Osoba("Student", "Studencki") with Student
		println(s"Podatek studenta: ${student1.imie} ${student1.nazwisko} wynosi ${student1.get_podatek}" + "\n")

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

	class BankAccount {
		private var _balance:Double = _
		def balance = _balance

		def this(initBalance:Double) {
			this()
			this._balance = initBalance
		}

		def withdraw(moneyToTake:Double) = {
			if (moneyToTake<=_balance) {
				_balance -= moneyToTake
        System.out.println("Wyplata pieniedzy")
      }
		}

		def transfer(moneyIn:Double) = {
			if (moneyIn>0.0) {
				_balance += moneyIn
        System.out.println("Wplata pieniedzy")
      }
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

	/*
	5.	Zdefiniuj klasę Osoba i trzy traity: Student, Nauczyciel, Pracownik. Osoba powinna mieć własności
			read only: imie, nazwisko, podatek. Pracownik powinien mieć własność pensja (z getterem i seterem).
			Student i Pracownik powinni przesłaniać własność podatek – dla Studenta zwracamy 0, dla Pracownika 20% pensji.
			Nauczyciel powinien dziedziczyć z Pracownika, dla niego podatek zwraca 10% pensji. Stwórz obiekty z każdym z traitów,
			pokaż jak podatek działa dla każdego z nich. Stwórz obiekty z traitami Student i Pracownik,
			pokaż jak podatek zadziała w zależności od kolejności w jakiej te traity zostały dodane przy tworzeniu obiektu.
	 */
	abstract class Osoba(val imie: String, val nazwisko: String)

	trait Pracownik {
		private var pensja = 100.0
		private val podatek = 0.2

		def get_pensja(): Double ={
			return pensja
		}
		def set_pensja(x: Double){
			pensja = x
		}
		def get_podatek(): Double ={
			return podatek * get_pensja()
		}

	}

	trait Student {
		def get_podatek = 0.0
	}

	trait Nauczyciel extends Pracownik {
		private val podatek = 0.1
		override def get_podatek = podatek * get_pensja
	}
}
