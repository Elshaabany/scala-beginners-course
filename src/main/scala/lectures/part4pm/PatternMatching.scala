package lectures.part4pm

import scala.util.Random

object PatternMatching extends App {
  // pattern matches are like switches in other programming languages
  // but is more powerful
  val random = new Random
  val x = random.nextInt(10)

  val description = x match {
    case 1 => "one"
    case 2 => "two"
    case 3 => "three"
    case _ => "something else"
  }

  println(x)
  println(description)

  // 1. Decompose values
  // case classes can be used in matching
  case class Person(name: String, age: Int)
  val bob = Person("Bob", 22)

  val greeting = bob match {
    case Person(n, a) if a < 21 => s"Hi, my name is $n and I am blow 21"
    case Person(n, a) => s"Hi, my name is $n and I am $a years old"
    case _ => "I don't know who I am"
  }
  println(greeting)
  // _ is a wildcard
  // if there is no cases match it will throw MatchError
  // the return type of the match expression is unified type of all the cases types

  // PM on sealed hierarchies
  sealed class Animal
  case class Dog(breed: String) extends Animal
  case class Parrot(greeting: String) extends Animal

  val animal: Animal = Dog("Terra Nova")
  animal match {
    case Dog(someBreed) => println(s"Matched a dog of the $someBreed breed")
  }

  // matches shouldn't be used on simple operations like:
  val isEven = x match {
    case n if n % 2 == 0 => true
    case _ => false
  }

  /*
    Exercise
    simple function uses PM
     takes an Expr => human readable form

     Sum(Number(2), Number(3)) => 2 + 3
     Sum(Number(2), Number(3), Number(4)) => 2 + 3 + 4
     Prod(Sum(Number(2), Number(1)), Number(3)) = (2 + 1) * 3
     Sum(Prod(Number(2), Number(1)), Number(3)) = 2 * 1 + 3
   */

  trait Expr
  case class Number(n: Int) extends Expr
  case class Sum(e1: Expr, e2: Expr) extends Expr
  case class Prod(e1: Expr, e2: Expr) extends Expr

  def show(e: Expr): String = e match {
    case Number(n) => s"$n"
    case Sum(e1, e2) => s"${show(e1)} + ${show(e2)}"
    case Prod(e1, e2) => {
      def maybeShowParentheses(expr: Expr) = expr match {
        case Prod(_, _) => show(expr)
        case Number(_) => show(expr)
        case _ => "(" + show(expr) + ")"
      }

      maybeShowParentheses(e1) + " * " + maybeShowParentheses(e2)
    }
  }

  println(show(Sum(Number(2), Number(3))))
  println(show(Sum(Sum(Number(2), Number(3)), Number(4))))
  println(show(Prod(Sum(Number(2), Number(1)), Number(3))))
  println(show(Prod(Sum(Number(2), Number(1)), Sum(Number(3), Number(4)))))
  println(show(Sum(Prod(Number(2), Number(1)), Number(3))))
  
}