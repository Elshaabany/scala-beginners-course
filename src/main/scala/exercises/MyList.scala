package exercises


abstract class MyList {
  /*
         head = first element of  the  list
         tail = remainder of the list
         isEmpty = is this list empty
         add(int) => new list with this element added
         toString => a string representation of the list
    */

  def head: Int
  def tail: MyList
  def isEmpty: Boolean
  def add(value: Int): MyList
  def printElements: String
  override def toString: String = "[" + printElements + "]"
}

object Empty extends MyList {
  def head: Int = throw new NoSuchElementException
  def tail: MyList = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add(value: Int): MyList = new cons(value, this)
  def printElements: String = ""
}

class cons(h: Int, t: MyList) extends MyList {
  def head: Int = h
  def tail: MyList = t
  def isEmpty: Boolean = false
  def add(value: Int): MyList = new cons(value, this)
  def printElements: String =
    if (t.isEmpty) "" + h
    else s"$h ${t.printElements}"
}

object test extends App {

  val list = new cons(1, new cons(2 , new cons (3, Empty)))
  println(list.add(4).head)
  println(list.isEmpty)

  println(list.toString)
}