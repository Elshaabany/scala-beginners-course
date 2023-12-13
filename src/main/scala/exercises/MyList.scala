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
}

object Empty extends MyList[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: MyList[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](value: B): MyList[B] = new cons(value, this)
  def printElements: String = ""
}

class cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  def head: A = h
  def tail: MyList[A] = t
  def isEmpty: Boolean = false
  def add[B >: A](value: B): MyList[B] = new cons(value, this)
  def printElements: String =
    if (t.isEmpty) "" + h
    else s"$h ${t.printElements}"
}

object test extends App {

  val listOfInt: MyList[Int] = new cons(1, new cons(2 , new cons (3, Empty)))
  val listOfString: MyList[String] = new cons("hello, ", new cons( "world ", new cons ("from scala", Empty)))
  println(listOfInt.add(4).head)
  println(listOfString.isEmpty)

  println(listOfInt.toString)
  println(listOfString.toString)

}