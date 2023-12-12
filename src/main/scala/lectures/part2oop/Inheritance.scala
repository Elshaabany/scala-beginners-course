package lectures.part2oop

object Inheritance extends App {

  class Animal {
    def creatureType = "wild"
    def eat = println("nomnomnom")
  }

  // scala support single  class inheritance
  // class can only inherit from single class at a time
  class cat extends Animal {
    def crunch = {
      eat
      println("crunch crunch")
    }
  }

   // now I can create cat instance
   // it will inherit non private fields and methods form it's parent
   // the default access modifier in scala is public
   // there is no public keyword in scala the public access modifier is specified with providing  no access modifier
   // we also have protected and private
   // private fields can only accessed inside it's own class
   // protected fields can be accessed form it's own class and child classes only

   val meo = new cat
   meo.crunch

  // constructors
  class Person(name: String, age: Int) {
    def this(name: String) = this(name, 0)
  }
  class Adult(name: String, age: Int, idCard: String) extends Person(name, age)


  // overriding
  // override can be done directly on the constructor definition as well
  // instead of sending the param then assigning it
//  class Dog(val type: String) extends Animal {
//        override def creatureType: String = type
//  }
  class Dog(override val creatureType: String) extends Animal {
    // vals, vars and methods can be overridden
//    override def creatureType: String = "domestic"
    override def eat = println("humhumhum")
    // super
    // super keyword can be used to access parent class methods
    def eat(food: String) = {
      super.eat
      println(s"eating $food")
    }

  }

  val dog = new Dog("K9")
  dog.eat
  println(dog.creatureType)

  // by default the class instance will use it's overridden methods

  // we can use reference of parent class type to point to instance of child class
  // however when using this reference on overridden method it will run the instance implementation of this method

  val unknownAnimal: Animal = new Dog("K9")
  unknownAnimal.eat




  // preventing overrides
  // 1 - use final on member
//  class Animal {
//    final def creatureType = "wild"
//  }
  // 2 - use final on the entire class
  // final class can't be extended = can't have child classes
//  final class Animal {
//    def creatureType = "wild"
//  }

  // 3 - seal the class
  // can be extended in This file only
  //  seal class Animal {
  //    final def creatureType = "wild"
  //  }



}
