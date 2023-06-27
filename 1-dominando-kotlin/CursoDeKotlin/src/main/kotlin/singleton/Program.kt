package singleton

import oop.User

fun main() {
    val userA = User("Beatriz")
    println(Database.name)
    println(Database.userLogged?.name)

    login(userA)
    println(Database.userLogged?.name)

    val userB = User("Anny Caroline")
    login(userB)
    println(Database.userLogged?.name)

    if (Constantes.MAX_NAME_LENGTH > 8) {
        println("MAX_NAME_LENGTH maior que 8")
    } else if (Constantes.MAX_NAME_LENGTH == 8) {
        println("MAX_NAME_LENGTH igual a 8")
    }
}

fun login(user: User) {
    Database.userLogged = user
}

// OBJECT = obj único (SINGLETON)
object Constantes {
    const val MAX_NAME_LENGTH = 8
    const val DOMAIN = "https://www.google.com.br"
}