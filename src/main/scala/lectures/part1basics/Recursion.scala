package lectures.part1basics

import scala.annotation.tailrec

object Recursion extends App {

  def factorial(n: BigInt): BigInt = {
    if (n <= 1) 1
    else n * factorial(n - 1)
  }
  // this recursive function will cause stack overflow error on calls with large numbers
//  println(factorial(5000))

  def anotherFactorial(n: BigInt): BigInt = {
    // this this called tail recursive
    // the compiler deal with it in special way
    // the compiler eliminates the need of making new stack frame so it saves stack memory
    @tailrec
    def factorialHelper(n: BigInt, accumulator: BigInt): BigInt =
      if (n <= 1) accumulator
      else factorialHelper(n - 1, n * accumulator)

    factorialHelper(n, 1)
  }

//  println(anotherFactorial(6000))

  // Task
  // using tail recursion Do:
  // 1- function that concatenate string n time
  def repeat(s: String, n: Int) = {
    @tailrec
    def repeatHelper(acc: String, n: Int): String =
      if (n <= 0) acc
      else repeatHelper(acc + s, n - 1)

    repeatHelper("", n)
  }

  println(repeat("Eslam", 4))

  // 2. is prime
//  @tailrec
//  def isPrime(n: Int, i: Int = 2): Boolean = {
//      if (i > n / 2) true
//      else if (n % i == 0) false
//      else isPrime(n, i + 1)
//  }

  def isPrime(n: Int): Boolean = {
    @tailrec
    def isPrimeHelper(t: Int, isStillPrime: Boolean): Boolean = {
      if(!isStillPrime) false
      else if(t <= 1) true
      else isPrimeHelper(t - 1, n % t != 0 && isStillPrime)
    }

    isPrimeHelper(n / 2, true)
  }

  println(isPrime(5))

  // 3. fibonacci function
  def fibonacci(n: Int): Int = {
    @tailrec
    def fiboTailRec(i: Int, last: Int, nextToLast: Int): Int = 
      if(i >= n) last
      else fiboTailRec(i + 1, last + nextToLast, last)

    if(n <= 2) 1
    else fiboTailRec(2, 1, 1)
  }

  println(fibonacci(8))
}
