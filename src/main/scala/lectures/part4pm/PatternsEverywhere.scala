package lectures.part4pm

object PatternsEverywhere extends App {

  // catches are MAtCHES
  try {
    // code
  } catch {
    case e: RuntimeException => "runtime"
    case npe: NullPointerException => "npe"
    case _ => ""
  }

  // for-comprehension uses match
  val List = List(1, 2, 3, 4)
  val even = for {
    x <- list if x % 2 == 0
  } yield 10 * x

  // generators are based on pattern matching

  val tuple = (1, 2, 3)
  // multi value definition
  val (a, b, c) = tuple
  // is also based on pattern matching
  println(b)

  // also based on pattern matching
  val head :: tail = List
  println(head)
  println(tail)
  
  // partial function
  val mappedList = list.map {
    case x if x % 2 == 0 => x + " is even"
    case 1 => "the one"
    case _ => "something else"
  } 
  // partial function literal

  val mappedList2 = list.map { x =>
    x match {
      case v if v % 2 == 0 => v + " is even"
      case 1 => "the one"
      case _ => "something else"
    }
  }

}
