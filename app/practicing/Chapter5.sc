import org.checkerframework.checker.units.qual.A

val t = List(1,2,3,4).map(_ + 10).filter(_ % 2 == 0).map(_ * 3)

val stream = 1 #:: 2 #:: 3 #:: LazyList.empty

stream.map(x => x+1).map(x => x*0)

stream.toArray
stream.take(1).toArray


sealed trait AStream[+A]
case object AEmpty extends AStream[Nothing]
case class ACons[+A](h: () => A, t: () => AStream[A]) extends AStream[A]

object AStream {
  def cons[A](hd: => A, tl: => AStream[A]): AStream[A] = {
    lazy val head = hd
    lazy val tail = tl
    ACons(() => head, () => tail)
  }

  def empty[A]: AStream[A] = AEmpty

  def apply[A](as: A*): AStream[A] =
    if (as.isEmpty) empty else cons(as.head, apply(as.tail: _*))

  //Exercise 5.2
  def take[A](n: Int) = {
    this match {
      case ACons(x, xs) if n > 0 => cons(x, xs.take(n - 1))
      case ACons(x, xs) if n == 0 => cons(x, AStream.empty)
      case _ => AEmpty
    }
  }

  def drop(n: Int): AStream[A] = {
    this match {
      case
    }
  }


  //Exercise 5.4
  def forAll[A](p: A => Boolean): Boolean = {
    this match {
      case ACons(x, Nil) if p(x) => true
      case ACons(x, xs) if p(x) => xs.forAll(p)
      case _ => false
    }
  }

  def forAll[A](s: Stream[A], f: A => Boolean): Boolean =
    s.foldRight()((a, b) => f(a) && b)


  val test = ACons(1, 2, 3)

  //Exercise 5.9
  def from(n: Int): AStream[Int] = {
    cons(n, from(n+1))
  }

}
val ones: Stream[Int] = Stream.cons(1, ones)
val test3 = ones

val n = 0
val ones2: LazyList[Int] = LazyList.cons(1, ones2)
val test4 = ones2.take(4).toList

