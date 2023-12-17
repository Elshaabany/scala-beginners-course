package lectures.part3fp

import scala.annotation.tailrec

object HOFsCurries extends App {

  // higher order function (HOF)
  // it's a function that take another function or return a function as result
  
  // map, flatmap, filter we built in MyList is HOF
  
  // make function that accept function and apply it n times on value x
  // nTimes(f, n, x)
  // nTimes(f, 3, x) = f(f(f(x)))
  
  @tailrec
  def nTimes(f: Int => Int, n: Int, x: Int): Int =
    if (n <= 0) x
    else nTimes(f, n-1, f(x))
    
  val plusOne = (x: Int) => x + 1
  
  println(nTimes(plusOne, 10, 1))
  
  // nTimes using carried function
  def nTimesBetter(f: Int => Int, n: Int): (Int => Int) = {
    if (n <= 0) (x: Int) => x
    else (x: Int) => nTimesBetter(f, n-1)(f(x)) 
  }
  
  val plus10 = nTimesBetter(plusOne, 10)
  println(plus10(3))
  
  // functions with multiple parameter lists
  def curriedFormatter(c: String)(x: Double) = c.format(x)
  
  val standardFormat: Double => String = curriedFormatter("%4.2f")
  val preciseFormat: Double => String = curriedFormatter("%10.8f")
  
  println(standardFormat(Math.PI))
  println(preciseFormat(Math.PI))
  
  
}
