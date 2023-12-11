package lectures.part2oop

import java.time.Year


object OOBasics extends App {

  val person = new Person("danial", 26)
  // class parameters are not fields unless declared with val or var
  println(person.age)
  person.greet("john")

  val author = new Writer("charles", "dickens", 1812)
  val imposter = new Writer("charles", "dickens", 1812)
  val novel = new Novel("great expectaiton", 1862, author)

  println(novel.authorAge)
  println(novel.isWrittenBy(author))

  val c = new Counter()
  println(c.count)
  c.inc.print
  c.inc(5).print
  c.inc(3).print

}

// the provided parameters represent the primary constructor
// only parameters declared with val or var are fields
// constructor can have default value
class Person(name: String,var age: Int = 0) {
  // this code block it the class body
  // it can have any thing any code block can
  // but it's value is ignored

  // val and var defined in this code block are class fields
  val x = 2

  // method

  def greet(name: String): Unit = println(s"${this.name} says: Hi $name")

  // method overloading
  // it can be done with providing a method with the same name and different signature
  // it can't be done with changing return type only
  def greet(): Unit = println(s"says: Hi $name") // no need for (this) here because there is no parameter with the same name


  // multiple constructor
  def this(name: String) = this(name, 0) // this calls the primary constructor
  def this() = this("john doe") // you can also call secondary constructor


  // any statement in this code block will be executed in every instance creation
  // these statement will do it's side effect on every object creation
  println(1 + 3)
  println(1 + 4)

  // these values will be ignored
  1 + 2
  if (true ) 7 else 8
}

/*
  Novel and a Writer

  Writer: first name, surname, year
    - method fullname

  Novel: name, year of release, author
  - authorAge
  - isWrittenBy(author)
  - copy (new year of release) = new instance of Novel
 */

  class Writer(val firstName: String,val surName: String,val yearOfBirth: Int) {
    def fullName = s"$firstName $surName"
  }

  class Novel(val name: String,val yearOfRelease: Int,val author: Writer){
    val authorAge: Int = yearOfRelease - author.yearOfBirth
    def isWrittenBy(author: Writer): Boolean = this.author.equals(author)
    def copy(year: Int): Novel = new Novel(name, year, author)
  }

/*
  Counter class
    - receives an int value
    - method current count
    - method to increment/decrement => new Counter
    - overload inc/dec to receive an amount
 */

  class Counter(val count: Int = 0) {
//    def getCount: Int = count     // by defining count as val there is no need for this getter it can be accessed directly
    def inc: Counter = new Counter(count + 1)
    def dec: Counter = new Counter(count - 1)
    def inc(n: Int): Counter = if(n <= 0) this else inc.inc(n - 1)
    def dec(n: Int): Counter = if(n <= 0) this else dec.dec(n - 1)
    def print = println(count)
  }