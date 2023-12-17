package lectures.part2oop

object CaseClasses extends App {

  case class Person(name: String, age: Int)

  // case class  is a special kind of class that automatically provide a number of features

  // 1- class parameters are promoted to immutable fields
  val eslam = new Person("eslam", 25)
  println(eslam.name)

  // 2- creates toString
  println(eslam.toString)
  println(eslam)

  // 3- equals and hashCode
  val eslam2 = new Person("eslam", 25)
  println(eslam == eslam2)              // prints true

  // 4- provides copy method
  // uses named parameters
  val ali = eslam.copy(name = "Ali")

  // 5- provides companion object
  val theObject = Person
  // with apply method implemented
  val marry = Person("marry", 22)
  println(marry)

  // case classes is serializable
  // case classes have extractor pattern = can be used in pattern matching

  // we also have case object
  case object UnitedKingdom {
    def name: String  = "The UK fo GB and NI"
  }
  // it has all features of case class except companion object
  println(UnitedKingdom.toString)
  
}


