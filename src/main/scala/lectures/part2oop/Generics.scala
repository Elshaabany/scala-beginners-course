package lectures.part2oop

object Generics extends App {

  // Generics allow us to set data type used by class when instantiating them
  //
  class MyList[A] {
    // this is a solution of the variance problem 
    // it allow the list to accept super type
    // and return new list of the super type
    def add[B >: A](element: B): MyList[B] = ???
  }

  val list: MyList[Int] = MyList[Int]

  // it can also be used by traits
  trait LinkedList[A]

  // a class can have more than one generic type
  class MyMap[k,v]

  // objects can't be declared with generic type
  // but it can use generics defined by companion class
  object MyList {
    def empty[A]: MyList[A] = ???
  }



  class Animal
  class Cat extends Animal
  class Dog extends Animal

  // variance problem
  // if B extends A, should List[B] extends List[A]?

  // normally in classes we can do this
  val animal: Animal = new Cat
  // we also can do this!!!
  val animalList: CovariantList[Animal] = new CovariantList[Cat]
  // will adding dog to the list is acceptable ?
//  animalList.add(new Dog)
  // we can specify the types to be substituted with using the variance annotations


  // variance annotations

  // covariant (+)
  // It allows the use of a more specific type (child type) as a substitute for a less specific type (parent type).
  class CovariantList[+A]
  // this will allow any sub types from Animal to be substituted to container of type Animal
  val anotherAnimalList: CovariantList[Animal] = new CovariantList[Cat]


  // invariant (no annotation)
  // It enforces that only the exact type specified can be used, and no subtype or supertype is allowed.
  class InvariantList[A]
  val invAnimalList: InvariantList[Animal] = new InvariantList[Animal]

  // contravariant (-)
  // It allows the use of a less specific type (parent class) as a substitute for a more specific type (child class).
  class Trainer[-A]
  val catTrainer: Trainer[Cat] = new Trainer[Animal]

  // bounded types
  // limit generic types to types that are subclass of the Animal class
  class Cage[A <: Animal](animal: A)

  // limit generic types to types that are super class of the Animal class
  class nature[A >: Animal](animal: A)

  val bird = new Animal
  val n = new nature[Animal](bird)



}
