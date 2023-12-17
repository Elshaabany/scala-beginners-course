package lectures.part2oop


object Exceptions extends App {

  val x: String = null
  // this will crash the program
//  println(x.length)


  // you can throw exception using throw keyword
//  throw new RuntimeException
  // throw is expression that return Nothing

  def getInt(throwException: Boolean) = {
    if (throwException) throw new RuntimeException("NAN")
    else 42
  }

  // the try catch is an expression that returns value
  // the return type of the expression will be the value of try an catch block combined
  // here the retuned type will be INT for try and String for catch so the combined type will be AnyVal
  val tryExprValue = try {
    // code that throw exception
    getInt(false)
    // any code in this block will not be executed
  } catch {
    case e: RuntimeException => println("caught Runtime exception!")
  } finally {
    // the code in finally will be executed in both cases
    // it's optional block
    // doesn't have effect on return type
    // use it only for side effects
    println("finally")
  }

  println(tryExprValue)

  // defining custom exception
  class MyException extends Exception
  val exception = new MyException

//  throw exception

  /*
      1.  Crash your program with an OutOfMemoryError
      2.  Crash with SOError
      3.  PocketCalculator
          - add(x,y)
          - subtract(x,y)
          - multiply(x,y)
          - divide(x,y)

          Throw
            - OverflowException if add(x,y) exceeds Int.MAX_VALUE
            - UnderflowException if subtract(x,y) exceeds Int.MIN_VALUE
            - MathCalculationException for division by 0
     */

  // 1 this will crash the program with OutOfMemoryException
//  val array = Array.ofDim[Int](Int.MaxValue)

  // 2 this will cause Stack overflow
  def infinite: Int = 1 + infinite


  // 3 Pocket calculator

  class OverFlowException extends RuntimeException("The result exceeded max value")
  class UnderFlowException extends RuntimeException("The result is below min value")
  class MathCalculationException extends RuntimeException("can't divide by zero")

  class PocketCalculator {
    def add(x: Int, y: Int) = {
      val result = x + y
      if(x > 0 && y > 0 && result < 0 ) throw new OverFlowException
      if(x < 0 && y < 0 && result > 0 ) throw new UnderFlowException
      result
    }

    def subtract(x: Int, y: Int) = {
      val result = x - y
      if (x > 0 && y < 0 && result < 0) throw new OverFlowException
      if (x < 0 && y > 0 && result > 0) throw new UnderFlowException
      result
    }

    def multiply(x: Int, y: Int) = {
      val result = x * y
      if (x > 0 && y > 0 && result < 0) throw new OverFlowException
      if (x < 0 && y < 0 && result < 0) throw new OverFlowException
      if (x > 0 && y < 0 && result > 0) throw new UnderFlowException
      if (x < 0 && y > 0 && result > 0) throw new UnderFlowException
      result
    }

    def divide(x: Int, y: Int) = {
      if(y == 0) throw new MathCalculationException
      x / y
    }
  }

  val pocketCalculator = new PocketCalculator
  println(pocketCalculator.add(4,2))
  println(pocketCalculator.subtract(4,2))
  println(pocketCalculator.multiply(4,2))
  println(pocketCalculator.divide(4,2))
}
