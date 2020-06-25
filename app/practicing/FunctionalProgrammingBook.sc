def factorialh(n: Int): Int = {
  def go(n: Int, acc: Int): Int =
  if (n <= 0) acc else go(n-1, n*acc)
  go(n, 1)
}




//Fibonacci
def fibonacci(number: Int): Int = {
  //  number match {
  //      case 1 => 0
  //      case 2 => 1
  //      case _ => fibonacci(number - 1) + fibonacci(number - 2)
  //  }
  //***** entender mejor esto
//  number match {
//    case x if x == 1 => 0
//  }
  if (number <= 2) 1
  else fibonacci(number - 1) + fibonacci(number - 2)
}

//print the result
for (x <- 1 to 6 by 1) {

  println("For " + x + " is " + fibonacci(x))
}

//Factorial
//@annotation.tailrec
def factorial(number: Int): Int = {
  if (number < 2) 1
  else number * factorial(number - 1)
}

println(factorial(8))

//high order functions
def someFunction(a: Int, b: Int, c: Int, f: (Int, Int) => Int): Int = {
  f(f(a, b), c)
}

//first way +++
println(someFunction(1, 2, 3, (x, y) => x + y))
//another wayn ---
println(someFunction(1, 2, 3, _ + _))



