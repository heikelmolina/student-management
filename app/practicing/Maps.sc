import scala.collection.mutable
import scala.collection.mutable._




val names = List("Andres", "Federico", "Ronald")


def aux(lista: List[String], value: String): List[Boolean] = {
  lista.map(x => x == value)
}

//
//def valueExist(str: String, str2: String): Boolean = {
//  str == str2
//}

aux(names, "Andres")





//Example
val ids = List(1, 2, 3, 4)
//val names = List("Andres", "Federico", "Ronald")
val students = Map(1 -> "Andres", 2 -> "  Federico")
val name = "Mississippi"

val a = Seq("a", "b", "c")


def matematica(value1: Int, value2: Int, function: (Int, Int) => Int): Int = {
  function(value1, value2)
}

def suma(valueA: Int, valueB: Int): Int = {
  valueA + valueB
}

def resta(valueA: Int, valueB: Int): Int = {
  valueA - valueB
}

matematica(5, 4, suma)
matematica(5, 4, resta)

suma(4, 5)

def toUpper(a: String): String = {
  a.toUpperCase
}

a.map(toUpper(_))




//Practicing Examples
names.map(x => x.toUpperCase).map(x => x.substring(0, 3))

//exercise 1
//count the letters in a word
def count(word: String)= {

}


//exercise 3
//remove the second element in a ListBuffer
def remove(list: ListBuffer[Int]) = {
  for (x <- (list.length - 1) to 0 by -2) {
    list.remove(x)
  }
//  for (x <- 0 to (list.length - 1) by 2) {
//    println(list(x))
//    list.remove(x + 1)
//  }
  list
}

val list = ListBuffer(1, 4, 7, 3, 4, 6, 9, 5)
println(remove(list))

//exercise 4
def returnInt(list: List[String], map: Map[String, Int]) = {
  //list.map(x => map.get(x).getOrElse("No existe"))
  list.flatMap(x => map.get(x))
  //list.map(x => map.get(x)).flatten
}

val courseNames = List("Math", "Spanish", "English", "A")
val courses = Map("Math" -> 1, "English" -> 2, "Programming" -> 3, "Spanish" -> 4)

println(returnInt(courseNames, courses))


//exercise 5
def likeMkString(input: List[String], middle: String = ", ") = {
  //input.reduceLeft((a, b) => a + middle + b)
  input.reduceLeft(_ + middle + _)

}

val people = List("Andres", "Federico", "Ronald", "Rosina", "Valentina")
println(likeMkString(people))
println(likeMkString(people, "0"))


//exercise 8
def dimensionalArray(values: Array[Double], numberOfColumns: Int) = {
  values.grouped(numberOfColumns).toArray
}

//val v: Array[Double] = Array(1.0, 4, 7, 3, 4, 6, 9, 10)
val v = Array(1.0, 4, 7, 3, 4, 6, 9, 10)

dimensionalArray(v, 2)

