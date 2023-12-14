package lectures.part2oop

object AnonymousClasses extends App {

  // anonymous classes are classes implement other classes (abstract or not) ,traits
  // it can be done as instantiation new object
  // by providing code block with implementation of abstract methods and needed overrides

  trait Animal {
    def eat: Unit
  }

  // it can be done with traits
  val cat = new Animal {
    def eat: Unit = println("nomnomnom")
  }

  abstract class Human {
    def walk: Unit
    def eat: Unit
  }

  // and anonymous class
  val mary  = new Human{
    def eat: Unit = println("humhumhum")

    def walk: Unit = println("walking")
  }


  class Person(name: String) {
    def sayHi = println(s"Hi my name is $name, nice to meet you!")
  }

  // also with normal classes
  val anon = new Person("tom") {
    override def sayHi: Unit = println(s"I am not tom and I don't know you!!")
  }

}
