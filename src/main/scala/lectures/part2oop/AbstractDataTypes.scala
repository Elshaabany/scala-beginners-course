package lectures.part2oop

object AbstractDataTypes extends App {


  // abstract classes can't be instantiated
  // it can have initialized and uninitialized fields
  abstract class Animal {
    val creatureType: String = "wild"
    def eat: Unit
  }

  // class that inherits from abstract class must override the uninitialized fields
  // or It could be abstract too
  // override keyword is not mandatory for fields that must be overridden
  // a class can only extend one abstract class
  class Dog extends Animal{
    override val creatureType: String = "Canine"
    def eat: Unit = println("crunch crunch")
  }

  // traits
  // trait is like abstract class
  // before scala 3 trait can not have constructor parameter
  trait Carnivore {
    def eat(animal: Animal): Unit
    val preferredMeal: String = "fresh meat"
  }

  // trait can be inherited along classes
  // a class can inherit form one or more trait
  trait ColdBlooded
  class Crocodile extends Animal with Carnivore with ColdBlooded {
    override val creatureType: String = "croc"

    def eat: Unit = println("nomnomnom")

    def eat(animal: Animal): Unit = println(s"I'm a croc and I'm eating ${animal.creatureType}")
  }

  val dog = new Dog
  val croc = new Crocodile
  croc eat dog

  // traits vs abstract classes
  // before scala 3 traits do not have constructor parameters
  // class can inherit multiple traits but can only implement on class
  // traits represents behavior, abstract class represents "thing"


}
