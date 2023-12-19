package lectures.part3fp

import java.lang.annotation.Target
import scala.annotation.tailrec

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

  /*
      1.  What would happen if I had two original entries "Jim" -> 555 and "JIM" -> 900

          !!! careful with mapping keys.

      2.  Overly simplified social network based on maps
          Person = String
          - add a person to the network
          - remove
          - friend (mutual)
          - unfriend

          - number of friends of a person
          - person with most friends
          - how many people have NO friends
          - if there is a social connection between two people (direct or not)
  */

  // 1
  val thePhonebook = Map(("Jim", 555), "Danial" -> 789, "JIM" -> 123).withDefaultValue(-1)
  // it will be added !
  println(thePhonebook)
  // this will result to data loss
  println(thePhonebook.map(pair => (pair._1.toLowerCase -> pair._2)))
  println(thePhonebook)



  // 2 The Social Network
  def add(network: Map[String, Set[String]], friend: String): Map[String, Set[String]] =
    network + (friend -> Set())

  def friend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
    val friendsA = network(a)
    val friendsB = network(b)

    network + (a -> (friendsA + b)) + (b -> (friendsB + a))
  }

  def unfriend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
    val friendsA = network(a)
    val friendsB = network(b)

    network + (a -> (friendsA - b)) + (b -> (friendsB - a))
  }

  def remove(network: Map[String, Set[String]], person: String): Map[String, Set[String]] = {
    @tailrec
    def removeAux(friends: Set[String], networkAcc: Map[String, Set[String]]): Map[String, Set[String]] = {
      if (friends.isEmpty) networkAcc
      else removeAux(friends.tail, unfriend(networkAcc, person, friends.head))
    }

    val unfriended = removeAux(network(person), network)
    unfriended - person
  }

  println("----------The-Social-Network----------")
  val emptyNetwork: Map[String, Set[String]] = Map()
  val network = add(add(emptyNetwork, "bob"), "mary")
  println(network)
  println(friend(network, "bob", "mary"))
  println(unfriend(friend(network, "bob", "mary"), "bob", "mary"))
  println(remove(friend(network, "bob", "mary"), "bob"))

  println("----------TestNet----------")
  // jim, bob, mary
  val people = add(add(add(emptyNetwork, "bob"), "mary"), "jim")
  val jimBob = friend(people, "jim", "bob")
  val testNet = friend(jimBob, "bob", "mary")

  println(testNet)

  // get number of friends for a person
  def nFriends(network: Map[String, Set[String]], person: String): Int =
    if(!network.contains(person)) 0
    else network(person).size

  println(nFriends(testNet, "bob"))


  // get person with most friends
  def mostFriends(network: Map[String, Set[String]]): String =
    network.maxBy(pair => pair._2.size)._1

  println(mostFriends(testNet))

  def nPeopleWithNoFriends(network: Map[String, Set[String]]): Int =
    network.count(_._2.isEmpty)

  println(nPeopleWithNoFriends(testNet))

  // find if there is a connection between two
  def socialConnection(network: Map[String, Set[String]], a: String, b: String): Boolean = {
    @tailrec
    def bfs(target: String, consideredPeople: Set[String], discoveredPeople: Set[String]): Boolean = {
      if (discoveredPeople.isEmpty) false
      else {
        val person = discoveredPeople.head
        if (person == target) true
        else if (consideredPeople.contains(person)) bfs(target, consideredPeople, discoveredPeople.tail)
        else bfs(target, consideredPeople + person, discoveredPeople.tail ++ network(person))
      }
    }
    
    bfs(b, Set(), network(a) + a)
  }
  
  println(socialConnection(testNet, "mary", "jim"))
  println(socialConnection(network, "mary", "bob"))

}