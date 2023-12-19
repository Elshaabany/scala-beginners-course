package lectures.part3fp

import scala.util.Random

object Sequences extends App {

  // the declared type of this sequence is Seq[Int]
  // the default sequence implementation is List
  val aSeq = Seq(1, 3, 2, 4)

  println(aSeq)
  println(aSeq.reverse)
  println(aSeq(2))
  println(aSeq ++  Seq(5, 6, 7))
  println(aSeq.sorted)

  // Ranges is sequence
  // inclusive
  val aRange1: Seq[Int] = 1 to 10

  // non inclusive
  val aRange2: Seq[Int] = 1 until 10

  // increase by 2
  val aRange3: Seq[Int] = 1 to 10 by 2


  println(aRange1.toList)
  println(aRange2.toList)
  println(aRange3.toList)


  // lists
  val aList = List(1, 2, 3)
  val prepended = 42 +: aList :+ 89
  println(prepended)

  val apples5 = List.fill(5)("apple")
  println(apples5)
  println(apples5.mkString("--"))


  // arrays
  val numbers = Array(1, 2, 3, 4)
  val threeElements = Array.ofDim[Int](3)
  // by default Array initialize values to 0 or false for primitive types
  // and to null for objects types

  threeElements.foreach(println)

  // mutation
  // arrays can be mutated in place
  // syntax surer for special numbers.update(2, 0)
  numbers(2) = 0
  println(numbers.mkString(" "))

  // arrays and seq

  // arrays can be implicitly converted to sequence
  val numbersSeq: Seq[Int] = numbers
  println(numbersSeq)


  // vectors
  // vector is the default implementation for the indexed immutable sequences
  // it's implemented using fixed-branched trie
  // it offer good performance for large sizes of data
  val vector: Vector[Int] = Vector(1,2,3)
  println(vector)


  // vectors vs lists

  // benchmarking vector and list speeds
  val maxRuns = 1000
  val maxCapacity = 1000000

  def getWriteTime(collection: Seq[Int]): Double = {
    val r = new Random
    val times = for {
      it <- 1 to maxRuns
    } yield {
      val currentTime = System.nanoTime()
      collection.updated(r.nextInt(maxCapacity), r.nextInt())
      System.nanoTime - currentTime
    }

    times.sum * 1.0 / maxRuns
  }

  val numbersList = (1 to maxCapacity).toList
  val numbersVector = (1 to maxCapacity).toVector
  
  println(getWriteTime(numbersList))
  println(getWriteTime(numbersVector))

  
}
