/**
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

	6.	Stwórz 7 elementową listę zawierającą nazwy dni tygodnia. Napisz funkcję tworzącą w oparciu o nią stringa z elementami oddzielonymi
	przecinkami korzystając z:
		a.	Pętli for
		b.	Pętli for wypisując tylko dni z nazwami zaczynającymi się na „P”
		c.	Pętli while

	7.	Dla listy z ćwiczenia 1 napisz funkcję tworzącą w oparciu o nią stringa z elementami oddzielonymi przecinkami korzystając z:
		a.	Funkcji rekurencyjnej
		b.	Funkcji rekurencyjnej wypisując elementy listy od końca

	8.	Stwórz funkcję korzystającą z rekurencji ogonowej do zwrócenia oddzielonego przecinkami stringa zawierającego elementy listy z ćwiczenia 1

	9.	Dla listy z ćwiczenia 1 napisz funkcję tworzącą w oparciu o nią stringa z elementami oddzielonymi przecinkami korzystając z:
		a.	Metody foldl
		b.	Metody foldr
		c.	Metody foldl wypisując tylko dni z nazwami zaczynającymi się na „P”

	10.	Stwórz mapę z nazwami produktów i cenami. Na jej podstawie wygeneruj drugą, z 10% obniżką cen. Wykorzystaj mechanizm mapowania kolekcji.
	11.	Zdefiniuj funkcję przyjmującą krotkę z 3 wartościami różnych typów i wypisującą je
	12.	Zaprezentuj działanie Option na dowolnym przykładzie (np. mapy, w której wyszukujemy wartości po kluczu)
	13.	Napisz funkcję usuwającą zera z listy wartości całkowitych (tzn. zwracającą listę elementów mających wartości różne od 0).  Wykorzystaj rekurencję.
	14.	Zdefiniuj funkcję, przyjmującą listę liczb całkowitych i zwracającą wygenerowaną na jej podstawie listę,
			w której wszystkie liczby zostały zwiększone o 1. Wykorzystaj mechanizm mapowania kolekcji.
	15.	Stwórz funkcję przyjmującą listę liczb rzeczywistych i zwracającą stworzoną
			na jej podstawie listę zawierającą wartości bezwzględne elementów z oryginalnej listy należących do przedziału <-5,12>. Wykorzystaj filtrowanie.
	*/

import scala.annotation.tailrec

object Main {
	def main(args: Array[String]): Unit = {
		val weekDays: List[String] = List("Poniedzialek", "Wtorek", "Sroda", "Czwartek", "Piatek", "Sobota", "Niedziela")

		// Ex. 1
		println("1. PatternMatching: " + weekDays(0) + " - " + matchDay(weekDays(0)))
		println("1. PatternMatching: " + weekDays(6) + " - " + matchDay(weekDays(6))+"\n")

		// Ex. 2
		val bankAccount = new BankAccount()
		val newBanckAccount = new BankAccount(123.45)

		println("2. Stan konta z poczatkowa suma: " + bankAccount.balance)
		println("2. Stan drugiego konta z domyslna suma: " + newBanckAccount.balance)

		bankAccount.transfer(100.0)
		newBanckAccount.withdraw(10.0)

		println("2. Stan konta z poczatkowa suma po wplacie: " + bankAccount.balance)
		println("2. Stan drugiego konta z domyslna suma po wyplacie: " + newBanckAccount.balance + "\n")

		// Ex. 3
		val person1 = new Person("Kasia","Kasiowa")
		val person2 = new Person("Basia","Basiowa")
		val person3 = new Person("Marysia", "Marysiowa")
		val person4 = new Person("Adam", "Adamski")

		println(s"3. Say Hello to $person1: " + sayHello(person1))
		println(s"3. Say Hello to $person2: " + sayHello(person2))
		println(s"3. Say Hello to $person3: " + sayHello(person3))
		println(s"3. Say Hello to stranger: " + sayHello(person4)+"\n")

		// Ex. 4
		var number = 10
		println(s"4. Add one three times to ${number} results in: " +  doTriple(calculate,number)+"\n")

		// Ex. 5
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

		// Ex. 6.A
		println("1.A: Separated with comas: " + separate(weekDays) + "\n")

		// Ex. 6.B
		println("1.B: Separated with comas (only 'P'): " + separateWithP(weekDays) + "\n")

		// Ex. 6.C
		println("1.C: Separated with comas (while): " + separateWhile(weekDays) + "\n")

		// Ex. 7.A
		println("2.A: Recusrion: " + recSeparate(weekDays) + "\n")

		// Ex. 7.B
		println("2.B: Recursion from end: " + recSeperateFromEnd(weekDays) + "\n")

		// Ex. 8
		println("3: Tail recursion: " + tailRecSeperate(weekDays) + "\n")

		// Ex. 9.A
		println("4.A: Fold left: " + foldLeftSeperate(weekDays) + "\n")

		// Ex. 9.B
		println("4.B: Fold right: " + foldRightSeparate(weekDays) + "\n")

		// Ex. 9.C
		println("4.C: Fold left (only 'P'): " + foldLeftSeperateWithP(weekDays) + "\n")

		// Ex. 10
		val products = Map("chleb"->2.00,"mleko"->3.00, "maslo"->5.00, "jajka"->8.00)

		println("5: Map without discount: " + products)
		println("5: Map with 10% discount: " + calculateDiscount(products) + "\n")

		// Ex. 11 :
		val arg1: Double = 1.00
		val arg2: Boolean = true
		val arg3: String = "arg3"
		printTuple(arg1,arg2,arg3+"\n")

		// Ex. 12
		val capitals = Map("Poland" -> "Warsaw", "Germany" -> "Berlin", "Italy" -> "Rome")
		println(s"7. Options: ${options(capitals, "Poland")}; ${options(capitals, "Hungary")};")

		// Ex. 13
		val numbers = List(0,1,10,4,0,5,6,0,0,34,20,0)
		println("\n8. Original numbers: " + numbers)
		println("8. Removed zeros from numbers: " + remove(numbers))

		// Ex. 14
		val nums = List(1,2,3,4,5,6,7,8,9,10)
		println("\n9: Original list: " + nums)
		println("9. Added 1 to each element: " + addOne(nums))

		// Ex. 15
		val absnums = List(-7,-6,-5,-4,-3,-2,-1,0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15)
		println("\n10. Original list: " + absnums)
		print("10. Abs value of each element: " + abs(absnums) +"\n")
	}

	/**
	 * BEGIN OF IMPLEMENTATION PART
	 */
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

	def sayHello(person: Main.Person): String = {
		person match {
			case Person(name,surname) if name == "Kasia" => s"Halo $name $surname!"
			case Person(name,surname) if name == "Basia" => s"Salut $name $surname!"
			case Person(name,surname) if name == "Marysia" => s"Hi $name $surname!"
			case default => "Hello Stranger!"
		}
	}

	def doTriple(f: Int => Int, n: Int) = f(f(f(n)))
	def calculate(n: Int) = n+1

	abstract class Osoba(val imie: String, val nazwisko: String)

	trait Pracownik {
		private var pensja = 100.0
		private val podatek = 0.2

		def get_pensja(): Double = {
			return pensja
		}
		def set_pensja(x: Double) {
			pensja = x
		}
		def get_podatek(): Double = {
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

	//Takes list and returns a String with week days separated with comas
	def separate(mylist: List[String]): String = {
		var word = ""
		for (element <- mylist) {
			word += element.concat(", ")
		}
		word.substring(0, word.length - 2)
	}

	def separateWithP(mylist: List[String]): String = {
		var word = ""
		for (element <- mylist if element.startsWith("P")) word += element.concat(", ")
		word.substring(0, word.length - 2)
	}

	def separateWhile(mylist: List[String]): String = {
		var word = ""
		var element = mylist;
		while (!element.isEmpty) {
			word += element.head + ", "
			element = element.tail
		}
		word.substring(0, word.length - 2)
	}

	def recSeparate(mylist: List[String]): String = {
		if (mylist.isEmpty) "" else mylist.head + "," + recSeparate(mylist.tail)
	}

	def recSeperateFromEnd(myList: List[String]): String = {
		if (myList.isEmpty) return "" else recSeperateFromEnd(myList.tail) + ", " + myList.head
	}

	def tailRecSeperate(myList: List[String]): String = {
		var word = ""
		@tailrec
		def iter(myList: List[String], result: String): String =
			if (myList.isEmpty) result else iter(myList.tail, result + ", "+ myList.head)
		word = iter(myList,"")
		word.substring(2,word.length)
	}

	def foldLeftSeperate(myList: List[String]): String = {
		var word = ""
		word = myList.foldLeft("")((dayBefore, dayAfter) => dayBefore + dayAfter + ", ")
		word.substring(0,word.length-2)
	}

	def foldRightSeparate(myList: List[String]): String = {
		var word = ""
		word = myList.reverse.foldRight("")((dayBefore, dayAfter) => dayAfter + dayBefore + ", ")
		word.substring(0,word.length-2)
		//Jesli mialy byc wypisane od niedzieli to wtedy bez .reverse()
	}

	def foldLeftSeperateWithP(myList: List[String]): String = {
		var word = ""
		word = myList.foldLeft("")((dayBefore,dayAfter) => if(dayAfter.startsWith("P")) dayBefore + ", " + dayAfter else dayBefore)
		word.substring(1,word.length)
	}

	def calculateDiscount(products: Map[String,Double]):Map[String,Double] = {
		products.map(product => (product._1, product._2 * 0.9))
	}

	def printTuple(arg: (Any, Any, Any)) = {
		println("6: Tuple: " + arg._1 + " " + arg._2 + " " + arg._3)
	}

	def options[T](map: Map[String,T], key: String)={
		map.get(key)
	}

	def remove(myList: List[Int]): List[Int] = {
		var list = List[Int]()
		val emptyList = List[Int]()
		@tailrec
		def iter(myList: List[Int], result: List[Int]): List[Int] =
			if (myList.isEmpty) result else if (myList.head != 0) iter(myList.tail, result :+ myList.head) else iter(myList.tail, result)
		list = iter(myList,emptyList)
		return list
	}

	def addOne(myList: List[Int]): List[Int] ={
		myList map(n => n+1)
	}

	def abs(myList: List[Int]): List[Int] ={
		myList filter (_ > -5) filter (_ <12) map (_.abs)
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
