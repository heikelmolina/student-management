// A function has a side effect if it does something other than simply return a result, for example:
// Modifying a variable
// Modifying a data structure in place
// Setting a field on an object
// Throwing an exception or halting with an error
// Printing to the console or reading user input
// Reading from or writing to a file
// Drawing on the screen

// Pure functions are easier to test, reuse, parallelize, generalize, and reason about.
// Furthermore, pure functions are much less prone to bugs.

def sum(a: Int, b: Int): Int = {
  a + b
}


//An expression e is referentially transparent if, for all programs p, all occurrences of e
//  in p can be replaced by the result of evaluating e without affecting the meaning of p.
//  A function f is pure if the expression f(x) is referentially transparent for all referentially transparent.
