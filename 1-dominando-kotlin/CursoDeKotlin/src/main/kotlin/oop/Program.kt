package oop

fun main() {

    val userA = User(name = "Bia", isAdmin = true)
    val userB = User(name = "Catarina")
    val userC = User()
    val userD = User("Beatriz", true)

    // COMPANION OBJECT - prop e comportamentos da CLASSE, ou seja, global.
    println(User.name)
    User.resetCount()

    // userA
    println("----userA------")
    userA.printUpperCase()
    userA.updateName(newName = "Beatriz")
    userA.printUpperCase()
    println("length of ${userA.name}: ${userA.getNameLength()}")
    println("isAdmin: ${userA.isAdmin}")

    // userB
    println("\n----userB------")
    println("length of ${userB.name}: " + userB.getNameLength())
    println("isAdmin: ${userB.isAdmin}")

    // userC
    println("\n----userC------")
    println("length of ${userC.name}: ${userC.getNameLength()}")

    // userD
    println("\n----userD------")
    userD.lastName = "Dadalto"
    userD.output()
}