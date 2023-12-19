package lectures.part3fp

object MapFlatMapFilterFor extends App {

  // scala has It's own List built in methods:
  // like we implemented in MyList exercise
  // we can create list using the List companion object
  val list = List(1, 2, 3)
  println(list.head)
  println(list.tail)

  // map
  println(list.map(_ + 1))
  println(list.map(_ + " is a number"))


  // filter
  println(list.filter(_ % 2 == 0))

  // flatMap
  val toPair = (x: Int) => List(x, x+1)
  println(list.flatMap(toPair))


  // print all combinations from two lists
  val numbers = List(1, 2, 3, 4)
  val chars = List('a', 'b','c', 'd')
  val colors = List("black", "white")

  val combinations = numbers.flatMap(n => chars.flatMap(c => colors.map( cl => s"$n$c-$cl")))
  println(combinations)

  // foreach
  list.foreach(println)

  // for-comprehensions
  // it provide more readability than using  maps and flatmap
  // the compiler translate this exception to use map and flatmap
  val forCombinations = for {
    n <- numbers
    c <- chars
    cl <- colors
  } yield s"$n$c-$cl"

  // prints the same result as combinations
  println(forCombinations)

  // use if to filter values
  val forCombinations2 = for {
    n <- numbers if n % 2 == 0
    c <- chars
    cl <- colors
  } yield s"$n$c-$cl"

  // it translate to filter function
  val combinations2 = numbers.filter(num => num % 2 == 0).flatMap(n => chars.flatMap(c => colors.map( cl => s"$n$c-$cl")))

  // using for comprehensions for side effects
  for {
    n <- numbers
  } println(n)

  // this is identical to
  numbers.foreach(println)
  
  // syntax overload
  list.map { x => 
    x * 2    
  }

  /*
      1.  MyList supports for comprehensions?
          map(f: A => B) => MyList[B]
          filter(p: A => Boolean) => MyList[A]
          flatMap(f: A => MyList[B]) => MyList[B]
      2.  A small collection of at most ONE element - Maybe[+T]
          - map, flatMap, filter
  */
}
