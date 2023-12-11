package lectures.part1basics

import scala.annotation.tailrec

object DefaultArgs extends App {


  // Default arg is value set in parameter definition to be used if the parameter not send
  // here is modified version of the factorial function to be using default arg
  @tailrec
  def factorial(n: BigInt, accumulator: BigInt = 1): BigInt =
    if (n <= 1) accumulator
    else factorial(n - 1, n * accumulator)

  println(factorial(5))

  // if we have a leading default parameter
  def saveImage(format: String = "jpg", width: Int, height: Int) = println("Saved")
  // can't do
  // saveImage(800, 600)
  // to solve this problem
  // make the default arg the last passed parameter in function definition
  // so the function can be usable without passing the default Arg
  // def saveImage(width: Int, height: Int, format: String = "jpg") = println("Saved")

  // pass every leading Arg even it is a default Arg
  saveImage("jpg", 800, 600)

  // use named Args
  // in function call you will send (argName = argValue)
  saveImage(width = 800, height = 600)

}
