package lectures.part4pm

object BracelessSyntax {

  // if
  val anIF = if ( 2 > 3) "big" else "small"

  // java style

  val anIfExp =
    if(2 > 3) {
      "bigger"
    } else {
      "smaller"
    }

  val compactIf =
    if (2 > 3) "bigger"
    else "smaller"


  // scala 3 introduces new syntax
  // removes the parentheses
  // depends on indentation

  val newIfExpr =
    if 2 > 3 then
      val result = "bigger"
      result
    else
      val result = "smaller"
      result

  // it can be also in one line
  val newIfExprOneline = if 2 > 3 then "bigger" else "smaller"



  // for comprehensions
  val aForComprehension = for {
    n <- List(1, 2, 3)
    s <- List("black", "white")
  } yield s"$n$s"

  // scala 3

  val aForComprehension_V2 =
    for
      n <- List(1, 2, 3)
      s <- List("black", "white")
    yield s"$n$s"

  // pattern matching
  val randomNum = 42
  val aPatternMatch = randomNum match
    case 1 => "one"
    case 2 => "double"
    case _ => "something else"


  def intendedMethodBody(arg: Int): Int =
    val someInt = 40
    
    
    
    
    someInt +1
    
  
  // class can also be defined with indentation only
  
  // in this case we add : after class name
  class Animal: 
    def eat(): Unit =
      println("eating")
      
    def grew() =
      println("growing")
  end Animal // end is optional for more readability 
  // it can be added to many blocks: if, match, for, methods, traits, enums, objects
  
  
  
  

  def main(args: Array[String]): Unit = {
    println(newIfExpr)
  }

}
