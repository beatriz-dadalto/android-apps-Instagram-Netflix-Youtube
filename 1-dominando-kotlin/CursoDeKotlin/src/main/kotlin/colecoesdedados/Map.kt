package colecoesdedados

fun main() {
    /*
        IMUTAVEIS | MUTAVEIS
        listOf   -> mutableListOf
        setOf    -> mutableSetOf
        mapOf    -> mutableMapOf
     */

    val product = mutableMapOf(
        "Android" to "Google",
        "iOS" to "Apple",
        "Windows" to "Microsoft"
    )

    // caso a chave nao exista
    val res = product.getOrDefault("Android2", "Não existe empresa para esse sistema operacional")
    // busque ou faça outra coisa dentro do bloco
    product.getOrElse("Android2") {
        println("VOCE ENVIA PARA O SERVIDOR")
    }
    println("res: $res")

    val hasWindows = product.containsKey("Windows")
    if (hasWindows) {
        println("Fazer alguma coisa quando tem o Windows")
    }

    val session = mapOf("token" to "13454564898999")
    val hasToken = session.containsValue("55sas55s")
    if (hasToken) {
        println("Exibir a tela principal do meu app")
    } else {
        println("Exibir a tela de login para o user")
    }

    // imprimir todas as chaves de um map
    println(product.keys)
    // imprimir todos os valores de um map
    println(product.values)

    product["Android"] = "Empreza XYZ"
    product.remove("Android")
    product.clear()

    if (product.isEmpty()) {
        println("Não há mais produtos na lista!")
    }

    // Maps funcionais

    val contactsGmail = mutableMapOf(
        1 to "Tiago",
        2 to "Gabriel",
        3 to "Adenilda",
        4 to "Roberto",
        5 to ""
    )

    val r = contactsGmail.filter { it.value.isNotEmpty() }.map { it.value.substring(0 ,1) }
    println(r)

    val users = User.createUsers(10)
    users.forEach { println(it) }
}

data class User(var name: String, var last: String) {
    companion object {
      private val users = mutableListOf<User>()

      fun createUsers(count: Int): List<User> {
          for (i in 0 .. count) {
              users.add(User("$i", "Desconhecido"))
          }
          return users
      }
    }
}