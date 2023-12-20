package lectures.part4pm

import exercises._

object AllThePatterns extends App {

  // All the patterns that can be matched with

  // 1. constants
  val x: Any = "Scala"
  val constants = x match {
    case 1 => "Number"
    case "Scala" => "Scala String"
    case true => "boolean type"
    case AllThePatterns => "Singleton Object"
  }

  // 2. match anything
  // 2.1 wildcard
  val matchAnything = x match {
    case _ => "any thing will pass"
  }

  // 2.2 variable
  val matchAVariable = x match {
    case someThing => s"this var $someThing will accept any thing"
  }

  // 3. tuples
  val aTuple = (1, 2)
  val matchATuple = aTuple match {
    case (1, 1) => "exact tuple"
    case (something, 2) => s"matches tuple with $something and 2"
  }

  //
  val nestedTuple = (1, (2, 3))
  val matchANestedTuple = nestedTuple match {
    case (_, (2, v)) => ""
  }


  // 4. case classes
  val aList: MyList[Int] = Cons(1, Cons(2, Empty))
  val matchAList = aList match {
    case Empty => "empty list"
    case Cons(head, Cons(subHead, subTail)) => s"match sub cons with values $head $subHead $subTail"
  }

  // 5. list patterns
  val aStandardList = 1 :: List(1, 2, 3, 45)
  val stdListMatching = aStandardList match {
    case List(1, _, _, _, _) => "extractor"
    case List(1, _*) => "list of arbitrary"
    case 1 :: List(_) => "infix pattern"
    case List(1, 2, _) :+ 42 => "infix pattern"
  }

  println(stdListMatching)

  // 6. type specifiers
  val unknown: Any = 2
  val unknownMatch = unknown match {
    case list: List[Int] => "match with exact type"
    case _ => "match any thing else"
  }

  // 7. name binding
  val nameBindingMatch = aList match {
    case nonEmptyList @ Cons(_, _) => s"giving the matcher name to use it $nonEmptyList"
    case Cons(1, rest @ Cons(2, _)) => s"it can be inside nested pattern $rest"
  }
  
  // 8 multi patterns
  val multiPattern = aList match {
    case Empty | Cons(0, _) => "compound pattern"
  }

  // 9. if guards
  val secondElementSpecial = aList match {
    case Cons(_, Cons(specialElement, _)) if specialElement % 2 == 0 => s"will match if $specialElement is even"
  }
  

  // JVM problem 
  // JVM is backward compatible
  // because Generics was introduced in later version
  // can't have matchers for the same type using generics
  // it will always match the first matcher of the type
  val numbers = List(1, 2, 3)
  val numbersMatch = numbers match {
    case listOfStrings: List[String] => "a list of strings"
    case listOfNumbers: List[Int] => "a list of numbers"
    case _ => ""
  }
}
