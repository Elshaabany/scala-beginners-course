package lectures.part3fp

object TuplesAndMaps extends App {

  // tuples = finite ordered "lists"
  val aTuple = new Tuple2(2, "hello, Scala")
  val aTuple4 = new Tuple4(2, "hello, Scala", 3, 4)

  println(aTuple)
  println(aTuple4)

  // better syntax for creating tuples
  val tup = (1, "hello")

  // Maps
  val aMap: Map[String, Int] = Map()

  // Maps accepts tuples as key value pair object
  // tuple can be written with the syntactic sugar key -> value
  val tup1 = "phone" -> 123
  println(tup1)

  // withDefaultValue get returned when getting unavailable key
  val phonebook = Map(("Jim", 555) , "Danial" -> 789).withDefaultValue(-1)
  println(phonebook)

  // map ops
  println(phonebook.contains("Jim")) // return boolean
  println(phonebook("Jim")) // return the value

  // add a paring
  val newPairing = "Marry" -> 678
  val newPhonebook = phonebook + newPairing
  println(newPhonebook)

  // functions on maps
  // map, flatmap, filter

  // make all keys lowercase
  println(phonebook.map(pair => (pair._1.toLowerCase -> pair._2)))


  println(phonebook.view.filterKeys(x => x.startsWith("J")).toMap)
  
  println(phonebook.view.mapValues(x => "02"+x).toMap)
  
  // conversions on collections 
  
  println(phonebook.toList)
  
  val names = List("Bob", "James", "Angela", "Mary", "Daniel", "Jim")
  println(names.groupBy(name => name.charAt(0)))
  
  
   
  
}
