package exercises


abstract class MyList[+A] {
  /*
         head = first element of  the  list
         tail = remainder of the list
         isEmpty = is this list empty
         add(int) => new list with this element added
         toString => a string representation of the list
    */

  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](value: B): MyList[B]
  def printElements: String
  override def toString: String = "[" + printElements + "]"

  def map[B](transformer: A => B): MyList[B]
  def flatMap[B](transformer: A => MyList[B]): MyList[B]
  def filter(predicate: A => Boolean): MyList[A]
  def ++[B >: A](list: MyList[B]): MyList[B]
}

case object Empty extends MyList[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: MyList[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](value: B): MyList[B] = new Cons(value, this)
  def printElements: String = ""

  def map[B](transformer: Nothing => B): MyList[B] = Empty

  def flatMap[B](transformer: Nothing => MyList[B]): MyList[B] = Empty

  def filter(predicate: Nothing => Boolean): MyList[Nothing] = Empty

  def ++[B >: Nothing](list: MyList[B]): MyList[B] = list


}

case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  def head: A = h
  def tail: MyList[A] = t
  def isEmpty: Boolean = false
  def add[B >: A](value: B): MyList[B] = new Cons(value, this)
  def printElements: String =
    if (t.isEmpty) "" + h
    else s"$h ${t.printElements}"

  def filter(predicate: A => Boolean): MyList[A] =
    if (predicate(h)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)


  def map[B](transformer: A => B): MyList[B] =
    new Cons(transformer(h), t.map(transformer))

  def flatMap[B](transformer: A =>  MyList[B]): MyList[B] =
    transformer(h) ++ t.flatMap(transformer)

  def ++[B >: A](list: MyList[B]): MyList[B] = new Cons(h, t ++ list)

}


object test extends App {

  val listOfInt: MyList[Int] = new Cons(1, new Cons(2 , new Cons (3, Empty)))
  val cloneListOfInt: MyList[Int] = new Cons(1, new Cons(2 , new Cons (3, Empty)))
  val listOfString: MyList[String] = new Cons("hello, ", new Cons( "world ", new Cons ("from scala", Empty)))
  println(listOfInt.add(4).head)
  println(listOfString.isEmpty)

  println(listOfInt.toString)
  println(listOfString.toString)

  println(listOfInt.map(_ * 2).toString)

  println(listOfInt.filter(_ % 2 == 0).toString)

  val anotherListOfInt = new Cons(10, new Cons(20, new Cons(30, Empty)))
  println(listOfInt ++ anotherListOfInt)
  
  println(listOfInt.flatMap( elem => new Cons(elem, new Cons(elem + 1, Empty)) ))

  println(listOfInt == cloneListOfInt)

}