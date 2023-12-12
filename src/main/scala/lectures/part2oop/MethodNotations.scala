package lectures.part2oop

import scala.language.postfixOps

object MethodNotations extends App {


  class Person(val name: String, val favMovie:String, val age: Int = 0){
    def likes(movie: String) = movie == favMovie
    def learns(course: String) = s"$name learns $course"
    def learnsScala = this learns "scala"
    def +(person: Person) = s"${this.name} is hanging out with ${person.name}"
    def +(nickName: String) = new Person(s"$name ($nickName)", favMovie)
    def unary_! : String = s"not $name"
    def unary_+ : Person = new Person(name, favMovie, age + 1)
    def isAlive = true
    def apply() = s"my name is $name and my fav movie is $favMovie" // it must be defined with ()
    def apply(n: Int) = s"$name watched $favMovie $n times"
  }

  val marry = new Person("marry", "Inception")
  println(marry.likes("Inception"))
  println(marry likes "Inception")
  // infix notation = operator notation
  // it can be done only with methods that has one parameter

  // operators
  // scala allow using operator like + & / as methods names
  // and it can be used in infix notation

  val tom = new Person("tom", "Fight Club")
  println(tom + marry)
  // this is equal to
  println(tom.+(marry))

  // operators in scala are methods
  // so this is valid syntax:
  println(1.+(2))


  // prefix notation
  val x = -1
  // unary operators are also methods
  val y = 1.unary_- // this is equal to using the prefix operator -1
  // unary_ prefix only works with + - ~ !

  println(!marry)
  println(marry.unary_!)

  // postfix notation
  println(marry.isAlive)
  // is equal to
  println(marry isAlive)
  // it is not commonly used because it may cause ambiguity to humans

  // apply
  // apply is special method in scala
  // it allows objects to be called as functions
  println(marry.apply())
  // this is equal to
  println(marry())
  // calling the object like this will call the object method apply

  println((marry + "the rockstar")())
  println(+marry)
  println(marry learns "scala")
  println(marry learnsScala)
  println(marry(2))


  /*
      1.  Overload the + operator
          mary + "the rockstar" => new person "Mary (the rockstar)"

      2.  Add an age to the Person class
          Add a unary + operator => new person with the age + 1
          +mary => mary with the age incrementer

      3.  Add a "learns" method in the Person class => "Mary learns Scala"
          Add a learnsScala method, calls learns method with "Scala".
          Use it in postfix notation.

      4.  Overload the apply method
          mary.apply(2) => "Mary watched Inception 2 times"
     */


}
