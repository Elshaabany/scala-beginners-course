package lectures.part1basics

object Functions extends App {

  // function definition must be followed by expression after the = sign
  // the expression can be code block { }

  def aFunction(a: String, b: Int): String = {
    a + " " + b
  }

  println(aFunction("hello", 3))


  def aParameterlessFunction() = 4
  println(aParameterlessFunction())

  // parameterless functions can be defined and called without parentheses
  def anotherParameterlessFunction = 5
  println(anotherParameterlessFunction)

  // in scala 3 functions defined by parentheses can not be called called without them and vice versa
  // this will case error:
  // println(aParameterlessFunction)

  // scala compiler can't infer the return type of recursive function
  def repeat(aString: String, n: Int): String = {
    if (n == 1) aString
    else aString + repeat(aString, n - 1)
  }

  // scala can have functions inside function
  // return type can be inferred form the inside function
  def aBigFunction(a: Int, b: Int) = {
    def aSmallFunction(a: Int, b: Int) = a + b

    aSmallFunction(a, b)
  }

  // Task
  // 1.  (name, age) => "Hi, my name is $name and I am $age years old"
  def sayHi(name: String, age: Int) =
    "Hi, my name is " + name + " and I am " + age + " years old."

  println(sayHi("eslam", 25))

  // 2. factorial function
  def factorial(n: Int): Int =
    if(n == 1) 1
    else n * factorial(n - 1)

  println(factorial(5))

  // 3. fibonacci function
  def fibonacci(n: Int): Int =
    if (n == 1 || n == 2) 1
    else fibonacci(n - 1) + fibonacci(n - 2)

  println(fibonacci(8))

  // 4. is prime function
//  def isPrime(n: Int, i: Int = 2): Boolean = {
//    if(i > n/2) true
//    else if (n % i == 0) false
//    else isPrime(n, i + 1)
//  }

  def isPrime(n: Int) = {
    def isPrimeUntil(t: Int): Boolean =
      if (t <= 1) true
      else n % t != 0 && isPrimeUntil(t - 1)

    isPrimeUntil(n / 2)
  }

  println(isPrime(23))

}
