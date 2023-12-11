package lectures.part1basics

object callBy extends App {


  // call by value
  // value is computed before call
  // same value will be used across the function
  def callByValue(x: Long) = {
    println(x)
    println(x)
  }

  callByValue(System.nanoTime())


  // call By name =>
  // the expression it self is passed
  // its executed every time it used in the function
  // so it can be evaluated with different values
  def callByName(x: => Long) = {
    println(x)
    println(x)
  }
  callByName(System.nanoTime())





  def infinite(): Int = 1 + infinite()
  def printFirst(x: Int, y: => Int) = println(x)

  // this will throw stack overflow error because the expression will be evaluated first before calling the function
  println(printFirst(infinite(), 33))

  // this will not throw error because the expression will not be evaluated
  println(printFirst(33, infinite()))


}
