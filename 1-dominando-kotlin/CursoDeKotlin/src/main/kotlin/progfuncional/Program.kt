package progfuncional

import oop.User

/*
    Filter
    ForEach
    Map
 */
fun main() {
    val users = mutableSetOf<User>(
        User(name = "Tiago", true),
        User(name = "Tiago", true),
        User(name = "Beatriz", false)
    )

    // Prog funcional a ideia eh criar NOVOS elementos e nao mais add/update
    users.add(
        User(name = "Novo usuário", false)
    )

    /*
        a prog funcional trata as funcoes de forma em que estas possam ser passadas como parametro para
        outras funcoes e podendo ter o resultado (diferente) armazenado em uma CONSTANTE
    */
    calc(5, 5, ::sum)
    calc(25, 5, ::minus)

    /*
        Entendendo lambda
        função anonima
    */
    calc(5, 5) {
        x, y -> x * y
    }

    val filter = users.filter { user -> user.isAdmin }
    filter.forEach { println(it) }
    val newFilter = users.filter(::filtrarAdmins)
    newFilter.forEach { user -> println(user) }

    println("--MAP--")
    // Functions transformations

    // map: List<User> -> List<String>
    val listNames = users.map { user -> user.name.uppercase() }.filter { it.contains("G") }
    listNames.forEach { println(it) }
}

// Tratar tudo como funcoes matematicas f(x) = z * y = x entrada, z e y saida
// estados imutaveis
fun sum(x: Int, y: Int): Int {
    return x + y
}

fun minus(x: Int, y: Int): Int {
    return x - y
}

fun calc(a: Int, b: Int, operation: (Int, Int) -> Int) {
    val result = operation(a, b)
    println("Resultado da operação $a com $b: $result")
}

fun filtrarAdmins(user: User): Boolean {
    return user.isAdmin
}

