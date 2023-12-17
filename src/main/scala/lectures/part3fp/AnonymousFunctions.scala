package lectures.part3fp

object AnonymousFunctions extends App {

  // scala introduces short hand syntax for Function type
  val doubler = new Function1[Int, Int] {
    override def apply(x: Int) = x * 2
  }

  // anonymous Function (LAMBDA)
  val doubleIt = (x: Int) => x * 2

  // if the type is set to the val I can write it like this
  val adder: Int => Int = x => x + 2

  // no params lambda
  val justDoSomething: () => Int = () => 3

  // lambda functions must be called with parentheses
  println(justDoSomething())

  // curly braces with lambda
  // this is a common syntax
  val stringToInt = { (str: String) =>
    str.toInt
  }

  println(stringToInt("9"))

  // MOAR syntactic sugar
  // the type is mandatory when using this 
  val niceIncrementer: Int => Int = _ + 1  // equal to x => x + 1
  val niceAdder: (Int, Int) => Int = _ + _  // equal to (a,b) => a + b

  /*
     1.  MyList: replace all FunctionX calls with lambdas
     2.  Rewrite the "special" adder as an anonymous function
  */
  
  val superAdd = (x: Int) => (y: Int) => x + y
  
  val add7 = superAdd(7)
  println(add7(3))
  println(superAdd(1)(2))
  

}
