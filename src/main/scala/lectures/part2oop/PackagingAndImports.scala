package lectures.part2oop

// import multiple files from the same package
// give a class an alias while importing it
import playground.{Cinderella => Princess, PrinceCharming}

// alias can solve conflicts
import java.util.Date
import java.sql.{Date => SqlDate}

object PackagingAndImports extends App {

  // class is accessible from any where in their package
  val marry = new Person("marry", 22)
  // otherwise you should import it's package
  // or use fully qualified name
  val zeroDate = java.sql.Date(0)

  // scala has special type of object: package object
  // it has the name of "package"
  // and signature
  // package object packageName {}
  // it can have filed that will be accessed form anywhere inside the package
  sayHello
  println(someGlobalValue)

  val prince = new PrinceCharming
  val princess = new Princess
  // can't use original name:
//  val cinderella = new Cinderella

  val date = new Date
  val sqlDate = new SqlDate(2024)

  // scala have some libraries that imported by default
  // java.lang : String, Object, Exception ...
  // scala : Int, Nothing, Function ...
  // scala.Predef : println, ???g


}
