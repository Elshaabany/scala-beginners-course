package lectures.part2oop

import scala.language.postfixOps

object MethodNotations extends App {


  class Person(val name: String, val favMovie:String){
    def likes(movie: String) = movie == favMovie
    def +(person: Person) = s"${this.name} is hanging out with ${person.name}"
    def unary_! : String = s"not $name"
    def isAlive = true
    def apply() = s"my name is $name and my fav movie is $favMovie" // it must be defined with ()
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



}
