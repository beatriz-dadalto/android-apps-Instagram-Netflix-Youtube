package colecoesdedados

import oop.User

fun main() {

    println("--Array | Fixo--")
    // array de tamanho fixo. usar a fun plus() para adicionar um novo index e retorna um novo array
    val items = arrayOf(20, "Bia", true, "Banana")
    val numbers = intArrayOf(1, 2, 3, 4, 5)
    items.forEach {
            item -> print("$item ")
    }
    println()
    numbers.forEach { number -> println(number) }

    val users = arrayOf<User?>(
        User("Bia", true),
        User("Anny", false)
    )
    users.forEach { println(it) }

    // adicao
    val newList = users.plus(User(name = "Cometa", isAdmin = false))
    newList.forEach { println(it) }

    // buscar
    println(users[0])
    users[0] = User("Novo user", false)
    println(users[0])

    println("--Lists | Dinamicos--")
    // Lists - array dinamicas - somente leitura, imutavel
    val usersList = listOf<User?>(User("Wolly", true), User("Lord", false))
    println(usersList.first())
    println(usersList.last())
    println(usersList.isEmpty())

    println("--Lista de dados Mutaveis--")
    val mutableList = mutableListOf<User?>(
        User(name = "Beatriz", true),
        User(name = "Caroline", false)
    )
    // update
    mutableList[0] = User(name = "Bia", true)
    // add
    mutableList.add(User(name = "Nick", false))
    // remove
    mutableList.removeAt(1)
    mutableList.forEach {
        println(it)
    }

    // Combinação de listas
    println("--Combinação de listas--")
    val a = mutableListOf("Mia", "Lord")
    val b = listOf("Be", "Maria", "Raquel")
    b.filterTo(a, {it.contains("a")})
    println(a)
    val all = listOf(a, b)
    println(all)
    println(all.flatten())

    // filtrando uma lista
    println("--filtrando uma lista--")
    val filtered = mutableList.filter {
        it -> it!!.isAdmin
    }
    filtered.forEach { println(it) }

    println("--Coleçoes de Sets--")
    // Set eh igual List mas nao permite add elementos repetidos
    val userSetlist = setOf("Tiago", "Bruce", "Lucas", "Bruce")
    userSetlist.forEach { println(it) }
    val usersCompare = setOf(
        User("Tiago", true),
        User("Tiago", true),
        User("Lucas", false),
    )
    usersCompare.forEach { println(it) }
}