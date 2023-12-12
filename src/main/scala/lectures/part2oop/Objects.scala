package lectures.part2oop

object Objects extends App {

  // SCALA Does not have call level functionality (static)
  // if I have class Person I can't have static field Person.Num
  // or static method Person.printNum()

  // instead scala has object
  // when defining object you are creating class + it's own only instance
  object Human {
    // class level functionality
    // it can have val, var or method
    val N_EYES = 2
    def canFly = false

    // factory method
//    def from(mother: Human, father: Human) = new Human("Bobbie")
    // instead we can call it apply
    def apply(mother: Human, father: Human) = new Human("Bobbie")
  }

  // in scala you can define class and object with the same name
  // this is pattern called companions
  // this allow to separate the functionality of instance level form class level
  class Human(val name: String = "") {
    // instance level functionality

  }

  println(Human.N_EYES)
  println(Human.canFly)

  // scala object is singleton instance


  // these are instances of Human type
  val human1 = Human
  val human2 = Human
  // they are pointing to the same instance
  println(human1 == human2)


  // after defining the companion class Human
  // we can create instances using new keyword
  val marry = new Human("marry")
  val tom = new Human("tom")
  // each one will be pointing to different instance
  println(marry == tom)

  // we can use special method apply with the Human object
  val Bobbie = Human(marry, tom)


  // scala Application
  // every scala app should have special method called main
  // with this signature
  // def main(args: Array[String]): Unit
  // this method is the start point of the application
  // this can be defined explicitly
  // or by defining scala object that extends App
  // the body of the object that extends App will be the body of the main method


}
