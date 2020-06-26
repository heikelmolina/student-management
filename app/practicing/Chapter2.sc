//object Chapter2 {
def abs(a: Int): Int = {
  if (a < 0)
    -a
  else
    a
}


//tail position: When we return a call to a recursive function
def factorial(n: Int): Int = {
  @annotation.tailrec
  def go(n:Int, acc: Int): Int = {
    if(n <= 0) acc
    else go(n - 1, n * acc)
  }
  go(n, 1)
}


//Exercise 2.1: Fibonacci using tail recursion
def fib(position: Int): Int = {
  @annotation.tailrec
  def go(position: Int, acc1: Int, acc2: Int): Int = {
    if (position <= 0)
      acc1
    else if (position == 1)
      acc2
    else
      go(position - 1, acc2, acc1 + acc2)
  }
  go(position, 0, 1)
}

//fib(4)
//go(4, 0, 1)
//go(3, 1, 1)
//go(2, 1, 2)
//go(1, 2, 3)
//fn = f n-1 + f n-2
//0, 1, 1, 2, 3, 5, , 8, 13

println(fib(3))

def formatResult(name: String, n: Int, f: Int => Int) = {
  val msg = "The %s of %d is %d."
  msg.format(name, n, f(n))
}

formatResult("fibo", 6, fib)

def findFirst[A](as: Array[A], p: A => Boolean): Int = {
  @annotation.tailrec
  def loop(n: Int): Int =
    if (n >= as.length) -1
    else if (p(as(n)))
      n
    else loop(n + 1)
  loop(0)
}

//Find the first 5 on the list and return the position
findFirst[Int](Array(1, 2, 3, 4, 5), (x: Int) => x == 5)

//Exercise 2.2
//@annotation.tailrec is used to tell the compiler to optimize the recursive function in this case
def isSorted[A](as: Array[A], ordered: (A,A) => Boolean): Boolean = {
  @annotation.tailrec
  def loop(n: Int): Boolean = {
    if (n <= 1)
      true
    else if (!ordered(as(n-2), as(n-1)))
      false
    else loop(n - 1)
  }
  loop(as.length)
}

def isSortedB[A](as: Array[A], ordered: (A,A) => Boolean): Boolean = {
  @annotation.tailrec
  def loop(n: Int): Boolean = {
    n match {
      case x if x <= 1 => true
      case x if !ordered(as(x-2), as(x-1)) => false
      case _ => loop(n - 1)
    }
  }
  loop(as.length)
}

def order(x: Int, y: Int): Boolean = {
  x < y
}

//bigger to smaller
isSorted[Int](Array(1, 2, 4, 3, 5), order) // false
isSorted[Int](Array(1, 2, 3, 4, 5), (x: Int, y: Int) => x < y) // true

//smaller to bigger
isSorted[Int](Array(4, 3, 2, 1), (x: Int, y: Int) => x > y) //true

//Exercise 2.3
def curry[A,B,C](f: (A, B) => C): A => B => C = {
  a => b => f(a, b)
}

//Exercise 2.4
def uncurry[A,B,C](f: A => B => C): (A, B) => C = {
  (a, b) => f(a), f(b)
}

//Exercise 2.5
def compose[A,B,C](f: B => C, g: A => B): A => C = {
  a => f(g(a))
  //for better understanding
  //a: A => f(g(a: A): B): C
}

//Las funciones polimorficas y HOF se usan en diferentes contextos sin importar
//el dominio de la operacion porque se abstraen a un patron comun que se comparte
//en diferentes contextos

//}
