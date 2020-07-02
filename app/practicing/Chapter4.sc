//Exercise 1
sealed trait Option[+A] {
  def map[B](f: A => B): Option[B] = {
    this match {
      case None => None
      case Some(x) => Some(f(x))
    }
  }



//  def flatMap[B](f: A => Option[B]): Option[B] = {
//  }
//
//  def getOrElse[B >: A](default: => B): B = {
//  }
//
//  def orElse[B >: A](ob: => Option[B]): Option[B] = {
//  }
//
//  def filter(f: A => Boolean): Option[A] = {
//  }
}
case class Pepe(name: Int) extends Option[Int]

val r = Pepe(6)

case class Some[+A](get: A) extends Option[A]
case object None extends Option[Nothing]

//Exercise 2
def variance(xs: Seq[Double]): Option[Double] = {
  ???
}

//Exercise 3
def map2[A,B,C](a: Option[A], b: Option[B])(f: (A, B) => C): Option[C] = {
  ???
}

sealed trait Either[+E, +A]
case class Left[+E](value: E) extends Either[E, Nothing]
case class Right[+A](value: A) extends Either[Nothing, A]

def test[A](a: Option[A]): Option[A] = {
  a match {
    case None => None
    case Some(x) => Some(x)
  }
}

val st = Stream