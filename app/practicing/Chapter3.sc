//Exercise 3.1
//Cons can be replaced by :: and means something like "New" in java
//val x = List(1,2,3,4,5) match {
//  case x  :: 2 :: 4 :: _ => x
//  case Nil => 42
//  case x :: y :: 3 :: 4 :: _ => x + y
//  case h :: t => h + sum(t)
//  case _ => 101
//}

//3
//Case 4 could match but first is 3

//Exercise 3.2
def tail[A](a: List[A]): List[A] = {
  a match {
    case Nil => Nil
    case _ :: xs => xs
  }
}


//Exercise 3.3
def setHead[A](a: List[A], b: A): List[A] = {
  //::(b, tail(a))
  b :: tail(a)
}

//Exercise 3.4
def drop[A](l: List[A], n: Int): List[A] = {
  if (n <= 0) l
  else
    l match {
      case Nil => Nil
      case _ :: xs => drop(xs, n-1)
    }
}

//Exercise 3.5
def dropWhile[A](l: List[A], f: A => Boolean): List[A] = {
  l match {
    case x :: xs if f(x) => dropWhile(xs, f)
    case _ => l
  }
}

val a = 1::2::3::4::5::1::6::7::Nil
println(drop(a, 3))

println(dropWhile(a, (x: Int) => x <= 2))

//Exercise 3.6
//************
def init[A](l: List[A]): List[A] = {
  l match {
    case _ :: Nil => Nil
    case x :: xs => x :: init(xs)
  }
}

init(List(1, 2, 3, 4, Nil))



//Exercise 3.7
/*No, porque como esta implementado siempre va a seguir multiplicando hasta que llegue al final de la lista.
Si se modifica la funcion case Cons(x, xs) if (x == 0) => ??? para hacer que si la cabeza es cero devuelva cero automaticamente
pero de la formma en la que esta implementado no.

Otra respuesta***: No, porque primero atraviesa la lista agregando cada elemento a la cola de llamada antes de que comience a aplicar la funcion
 */

//default
def foldRight[A,B](as: List[A], z: B)(f: (A, B) => B): B = {
  as match {
    case Nil => z
    case x :: xs => f(x, foldRight(xs, z)(f))
  }
}

//default
def sum(ns: List[Int]) = {
  foldRight(ns, 0)((x, y) => x + y)
}


//Exercise 3.8
//No logro entender la pregunta que hacen, pero una posible respuesta pudiera ser:
//Cuando esto pasa simplemnte se crea una nueva lista con los mismos elementos que la input
foldRight(List(1, 2, 3), Nil: List[Int])(::(_,_))

//Exercise 3.9
def length[A](as: List[A]): Int = {
  foldRight(as, 0)((_, x) => x + 1)
}

val b = 1::2::3::4::5::1::6::7::Nil
 println(length(b))

//Exercise 3.10
//Mas dudas: Como hacerlo tailrec? En caso de que fuera identico al cap 2, por que hacerlo de esa manera y no de esta automaticamante y ponerle la anotacion?

@scala.annotation.tailrec
def foldLeft[A,B](as: List[A], z: B)(f: (B, A) => B): B = {
 as match {
   case Nil => z
   case x :: xs => foldLeft(xs, f(z, x))(f)
 }
}

foldLeft(List(1, 2, 3, 4), 0)((x, y) => x+y)

//Exercise 3.11
