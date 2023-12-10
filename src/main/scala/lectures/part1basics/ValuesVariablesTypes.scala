package lectures.part1basics

object ValuesVariablesTypes extends App{
  val x: Int = 42
  println(x)

  // val is Immutable
  // can't be reassigned x = 1

  // compiler can infer the type
  // semi-colons are optionals
  val y = 40;

  // ## primitive types
  val aString: String = "hello"
  val aBoolean: Boolean = false
  // numerical types
  val aByte: Byte = 127                           // 1 byte
  val aChar: Char = 97 //= val aChar: Char = 'a'  // 2 bytes
  val aShort: Short = 32767                       // 2 bytes
  val aInt: Int = 2147483647                      // 4 bytes
  val aLong: Long = 9223372036854775807L          // 8 bytes
  val aFloat: Float = 3.4028235E38f               // 4 bytes
  val aDouble: Double = 1.7976931348623157E308    // 8 bytes

  // ## variables
  var aVariable: Int = 5
  // variables can be reassigned
  aVariable = 4

}
