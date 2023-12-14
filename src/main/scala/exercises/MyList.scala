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

  def map[B](transformer: MyTransformer[A, B]): MyList[B]
  def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B]
  def filter(predicate: MyPredicate[A]): MyList[A]
  def ++[B >: A](list: MyList[B]): MyList[B]
}

object Empty extends MyList[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: MyList[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](value: B): MyList[B] = new Cons(value, this)
  def printElements: String = ""

  def map[B](transformer: MyTransformer[Nothing, B]): MyList[B] = Empty

  def flatMap[B](transformer: MyTransformer[Nothing, MyList[B]]): MyList[B] = Empty

  def filter(predicate: MyPredicate[Nothing]): MyList[Nothing] = Empty

  def ++[B >: Nothing](list: MyList[B]): MyList[B] = list


}

class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  def head: A = h
  def tail: MyList[A] = t
  def isEmpty: Boolean = false
  def add[B >: A](value: B): MyList[B] = new Cons(value, this)
  def printElements: String =
    if (t.isEmpty) "" + h
    else s"$h ${t.printElements}"

  def filter(predicate: MyPredicate[A]): MyList[A] =
    if (predicate.test(h)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)


  def map[B](transformer: MyTransformer[A, B]): MyList[B] =
    new Cons(transformer.transform(h), t.map(transformer))

  def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B] =
    transformer.transform(h) ++ t.flatMap(transformer)

  def ++[B >: A](list: MyList[B]): MyList[B] = new Cons(h, t ++ list)

}

trait MyPredicate[-T] {
  def test(elem: T): Boolean
}

trait MyTransformer[-A, B] {
  def transform(elem: A): B
}

object test extends App {

  val listOfInt: MyList[Int] = new Cons(1, new Cons(2 , new Cons (3, Empty)))
  val listOfString: MyList[String] = new Cons("hello, ", new Cons( "world ", new Cons ("from scala", Empty)))
  println(listOfInt.add(4).head)
  println(listOfString.isEmpty)

  println(listOfInt.toString)
  println(listOfString.toString)

  println(listOfInt.map(new MyTransformer[Int, Int] {
    override def transform(elem: Int): Int = elem * 2
  }).toString)

  println(listOfInt.filter(new MyPredicate[Int]{
    override def test(elem: Int): Boolean = elem % 2 == 0
  }).toString)

  val anotherListOfInt = new Cons(10, new Cons(20, new Cons(30, Empty)))
  println(listOfInt ++ anotherListOfInt)
  println(listOfInt.flatMap(new MyTransformer[Int, MyList[Int]] {
    override def transform(elem: Int): MyList[Int] = new Cons(elem, new Cons(elem + 1, Empty))
  }) )

}