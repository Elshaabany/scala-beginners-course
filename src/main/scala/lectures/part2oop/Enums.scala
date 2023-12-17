package lectures.part2oop

object Enums {

  // enum is data type
  // it can have fields and methods
  enum Permissions {
    case READ, WRITE, EXECUTE, NONE

    def openDocument(): Unit = {
      if (this == READ) println("opening document...")
      else println("reading not allowed.")
    }
  }

  // enum can have constructor function

  enum PermissionsWithBits(bits: Int) {
    case READ extends PermissionsWithBits(4)
    case WRITE extends PermissionsWithBits(2)
    case EXECUTE extends PermissionsWithBits(1)
    case NONE extends PermissionsWithBits(0)
  }

  // enum can have companion object
  object PermissionsWithBits{
    def withBits(bits: Int): PermissionsWithBits =
      PermissionsWithBits.NONE
  }

  val write = PermissionsWithBits.WRITE

  val somePermissions : Permissions = Permissions.READ

  // enum standard API
  val somePermissionOrdinall: Int = somePermissions.ordinal  // get the index of value
  val allPermission: Array[Permissions] = Permissions.values                // array fo all values
  val readPermission: Permissions = Permissions.valueOf("READ")      // get enum value by name
  
  def main(args: Array[String]): Unit = {
    somePermissions.openDocument()
    println(write)
    println(somePermissionOrdinall)
    println(allPermission.toList)
    println(readPermission)
  }

}
