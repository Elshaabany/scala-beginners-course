package lectures.part1basics

object StringOps extends App {

  // scala provides useful string functions:

  val str = "Hello, I'm learning scala!"


  // it uses functions from java
  println(str.charAt(2))
  println(str.substring(3,5))
  println(str.split(" ").toList)
  println(str.startsWith("Hello"))
  println(str.replace(" ", "-"))
  println(str.toLowerCase())
  println(str.toUpperCase())
  println(str.length)


  // it also has its own utilities
  val numString = "123"
  val numInt = numString.toInt
  println('a' +: numString :+ 'z')
  println(numString.reverse)
  println(numString.take(2))

  // scala specific: interpolates

  // s-interpolates

  val name = "eslam"
  val age = 25
  val greeting = s"Hello, my name is $name and my age is $age"
  val anotherGreeting = s"Hello, my name is $name and my age will be ${age + 1}"

  println(greeting)
  println(anotherGreeting)

  // f-interpolates
  val speed = 1.2f
  val myth = f"my car accelerates to 100km in $speed%.3f sec"
  println(myth)

  // raw-interpolates
  println(raw"this is interpolated \n literally") // \n not escaped
  val msg = "this is interpolated \n literally"
  println(raw"$msg") // in this case it will be escaped
}
