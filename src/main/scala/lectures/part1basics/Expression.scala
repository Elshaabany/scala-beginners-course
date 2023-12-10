package lectures.part1basics

object Expression extends App{
   val x = 1 + 2 // EXPRESSION
   println(x)

   // math expression
   println( 1 + 2 * 3)
   // math operators: + - * / & | >> << >>>(right shift with zero)
   // >>> fills the left most bits with zero instead of >> fills with the sign bit value

   // compression expression
   println( 1 == x)
   // == != > >= < <=

   // logical expression
   println(!(x == 1 || x == 2  && x != 0 ))
   // ! && ||

   // compound assignment operators
   var aVar = 3
   aVar += 2
   println(aVar)
   // += -+ *= /=
   // this operators only doesn't work variables because it has side effects

   // Instructions(statements) (DO) vs Expressions (Value)

   // instruction are executed
   // expressions are evaluated

   // in scala if is an expression that return values
   val aCondition = true
   val aConditionedValue = if (aCondition) "yes" else "no"

   // scala discourse imperative programming

   // Every thing in scala is expression
   // except only definitions like class, package or val

   // even variable assignment is expression
   // it return special type called Unit
   // Unit is similar to void in other languages
   // Unit has special value which is ()
   val returnedValue = (aVar = 1)
   println(returnedValue)


   // side effects in scala are expression returning Unit
   // like: println(), whiles, reassigning

   // code blocks
   val codeBlock = {
      val y = 2
      val x = 3
      if (x > y) "yes" else "no"
   }
   // code blocks returns the value of last exception

   println(codeBlock)

}
