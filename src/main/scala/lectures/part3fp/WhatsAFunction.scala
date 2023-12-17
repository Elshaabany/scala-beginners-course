package lectures.part3fp

object WhatsAFunction extends App {

  // we want to work with functions as first class elements
  // the problem is scala is build on top of OOP environment


  // the solution is use functions inside instances of classes
  trait MyFunction[A, B] {
    def apply(el: A): B
  }
  // instance form anonymous that implements MyFunction
  val doubler = new MyFunction[Int, Int] {
    override def apply(el: Int): Int = el * 2
  }
  // by using the special method apply we can use this as function
  println(doubler(3))

  // scala support some function type out of the box
  // using FunctionN naming
  // the N represent the number of parameters
  // Scala provide Function type up to 22 parameters
  val fun = new Function0[Int] {
    override def apply(): Int = 4
  }

  val concatenator: Function2[String, String, String] = new Function2[String, String, String] {
    override def apply(v1: String, v2: String): String = s"$v1$v2"
  }

  println(concatenator("Hello ", "World"))

  // instead of type Function2[Int, Int, Int] we can use (Int, Int) => Int
  val add: (Int, Int) => Int = new Function2[Int, Int, Int] {
    override def apply(v1: Int, v2: Int): Int = v1 + v2
  }
  
  // All scala functions are objects


  /*
      1.  a function which takes 2 strings and concatenates them
      2.  transform the MyPredicate and MyTransformer into function types
      3.  define a function which takes an int and returns another function which takes an int and returns an int
          - what's the type of this function
          - how to do it
  */
  
  // 1 
  
  def concat = new ((String, String) => String) {
    override def apply(v1: String, v2: String): String = s"$v1$v2"
  }
  
  println(concat("Hi ", "scala"))
  
  
  // 3

  // the returned function called returned functions

  val superAdder = new Function1[Int, Function1[Int, Int]] {
    override def apply(v1: Int): Function1[Int, Int] = new Function1[Int, Int] {
      override def apply(v2: Int): Int = v1 + v2
    }
  }
  
  val add2 = superAdder(2)
  
  println(add2(3))
  
  // this is the same
  val specialFunction =  new (Int => Int => Int) {
    override def apply(x: Int): Int => Int = new (Int => Int) {
      override def apply(y: Int): Int = x + y
    }
  }
  
  val add4 = specialFunction(4)
  
  println(add4(6))
   
  
  

}
