package lectures.part3fp

import scala.util.Random

object Options extends App {

  val myFirstOption: Option[Int] = Some(4)
  val noOption: Option[Int] = None

  println(myFirstOption)

  // working with unsafe API
  def unsafeMethod(): String = null

  //  val result = Some(null) // WRONG
  // should use Option(value)
  // and if value = null the result will be None
  val result = Option(unsafeMethod())
  // option returns Some(value) or None
  println(unsafeMethod())   // shouldn't be used
  println(result)

  // chained methods
  def backupMethod(): String = "A valid result"
  // orElse can be used on options
  val chainedResult = Option(unsafeMethod()).orElse(Option(backupMethod()))

  println(chainedResult)

  // Better Design
  def betterUnsafeMethod(): Option[String] = None
  def betterBackupMethod(): Option[String] = Some("A valid result")
  val betterChainedResult = betterUnsafeMethod() orElse betterBackupMethod()

  println(betterChainedResult)

  // functions on Options
  println(myFirstOption.isEmpty)
  println(myFirstOption.get)    // shouldn't be used: will throw exception if None

  // Options has map, flatMap, filter
  println(myFirstOption.map(_ * 2))
  println(myFirstOption.filter(_ > 10))
  println(myFirstOption.flatMap(x => Option(x * 10)))

  // that mean it can be used with for-comprehensions

  /*
      Exercise.
  */
  val config: Map[String, String] = Map(
    // fetched from elsewhere
    "host" -> "176.45.36.1",
    "port" -> "80"
  )

  class Connection {
    def connect = "Connected" // connect to some server
  }

  object Connection {
    val random = new Random(System.nanoTime())

    def apply(host: String, port: String): Option[Connection] =
      if (random.nextBoolean()) Some(new Connection)
      else None
  }

  // try to establish a connection
  val host = config.get("host")
  val port = config.get("port")

  val connection = host.flatMap(h => port.flatMap(p => Connection(h, p))) // returns some or none

  println("-------------connecting-------------")
  println(connection.map(c => c.connect))  // prints some(value) or None
  connection.map(c => c.connect).foreach(println) // prints value or doesn't print
  
  // let's do all it in chained calls

  config.get("host")
    .flatMap(host => config.get("port")
      .flatMap(port => Connection(host, port))
      .map(connection => connection.connect))
    .foreach(println)

  // another syntax
  config.get("host")
    .flatMap { host => 
      config.get("port").flatMap { port => 
          Connection(host, port)
        }.map { connection => 
        connection.connect
      }
    }.foreach(println)

  println("---for---")
  
  // for-comprehensions
  // makes it more readable
  val forConnectionStatus = for {
    host <- config.get("host")
    port <- config.get("port2")
    connection <- Connection(host, port)
  } yield connection.connect
  
  forConnectionStatus.foreach(println)
}
