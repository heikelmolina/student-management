//object Chapter2 {
  def abs(a: Int): Int = {
    if (a < 0)
      -a
    else
      a
  }

  def formatAbs(a: Int) = {
    val msg = "The absolute value of %d is %d."
    msg.format(a, abs(a))
  }


//tail position: When we return a call to a recursive
def factorial(n: Int): Int = {
  @annotation.tailrec
  def go(n:Int, acc: Int): Int = {
    if(n <= 0) acc
    else go(n - 1, n * acc)
  }
  go(n, 1)
}
println(factorial(5))

//Exercise 2.1
def fib(n: Int): Int = {
  @annotation.tailrec
  def go(n: Int, acc: Int): Int = {
    if (n == 0) 0
    else if (n > 2) acc
    else go(n - 1, (n - 1) + (n - 2))
  }
  go(n, 1)
}

println(fib(2))


formatAbs(4)
//}
