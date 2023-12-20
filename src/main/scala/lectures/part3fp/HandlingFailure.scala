package lectures.part3fp

import scala.util._

object HandlingFailure extends App {
  //  Try
  // Success extends Try
  // Failure extends Try

  // we can create Success and failure explicitly

  val aSuccess = Success(3)
  val aFailure = Failure(new RuntimeException("Error!!"))
  
  println(aSuccess)
  println(aFailure)
  
  def unsafeMethod(): String = throw new RuntimeException("No String !!")
  
  val potentialFailure = Try(unsafeMethod())
  println(potentialFailure)

  val potentialFailure2 = Try { 
    "syntax sugar for Try"
  }
  println(potentialFailure2)
  
  // utilities 
  println(potentialFailure.isSuccess)
  
  // orElse
  def backupMethod(): String = "A valid result"
  val fallbackTry = Try(unsafeMethod()).orElse(Try(backupMethod()))
  println(fallbackTry)
  
  // IF you design the API
  // make it return Try
  def betterUnsafeMethod(): Try[String] = Failure(new RuntimeException)
  def betterBackupMethod(): Try[String] = Success("A valid result")
  val betterFallback = betterUnsafeMethod() orElse betterBackupMethod()
  println(betterFallback)
  
  // Try have map, flatMap, filter
  println(aSuccess.map(_ * 2))
  println(aSuccess.flatMap(x => Success(x * 10)))
  println(aSuccess.filter(_ > 10))
  // that make Try use for-comprehensions


  /*
    Exercise
   */
  val host = "localhost"
  val port = "8080"

  def renderHTML(page: String) = println(page)

  class Connection {
    def get(url: String): String = {
      val random = new Random(System.nanoTime())
      if (random.nextBoolean()) "<html>...</html>"
      else throw new RuntimeException("Connection interrupted")
    }

    def getSafe(url: String): Try[String] = Try(get(url))
  }

  object HttpService {
    val random = new Random(System.nanoTime())
    
    def getConnection(host: String, port: String): Connection =
      if (random.nextBoolean()) new Connection
      else throw new RuntimeException("Someone else took the port")

    def getSafeConnection(host: String, port: String): Try[Connection] = Try(getConnection(host, port))
  }
  
  val possibleConnection = HttpService.getSafeConnection(host, port)
  val possibleHTML = possibleConnection.flatMap(connection => connection.getSafe("/home"))
  possibleHTML.foreach(renderHTML)
  
  // using chaining
  HttpService.getSafeConnection(host, port)
    .flatMap(connection => connection.getSafe("/home"))
    .foreach(renderHTML)

  // for-comprehension 
  for {
    connection <- HttpService.getSafeConnection(host, port)
    data <- connection.getSafe("/")
  } renderHTML(data)
  
}
