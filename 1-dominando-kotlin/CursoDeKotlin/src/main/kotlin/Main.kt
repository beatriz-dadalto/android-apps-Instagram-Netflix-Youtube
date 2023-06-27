import oop.User

fun main() {
    //val imutavel
    // var mutavel

    // operadores logicos (+ - / * %)
    var age = 33
    var ageText = "33"
    println(ageText + " minha idade")

    // true = 1 false = 0
    val isBoolean = false
    println(!isBoolean)

    val name = "Bia Dadalto"
    val currentAge = 32
    println("Olá, $name. Sua idade é ${currentAge + 1}?")

    // Condicoes (Controle de fluxo)
    // <, >, <=, >=, !, !=, ==

    // WHEN - (if | else if | else)
    val user = User(name = "Bia", isAdmin = false)
    when(user.name) {
        "Bia" -> {
            println("${user.name} encontrado no banco de dados")
        }
        "Mônica" -> {
            println("${user.name} está ao vivo")
        }
        else -> println("Nenhum usuário encontrado no banco de dados")
    }

    // WHEN - (if | else)
    when(user.name) {
        "Bia" -> println("${user.name} encontrado no banco de dados")
        else -> println("Nenhum usuário encontrado no banco de dados")
    }

    // comparar String
    val mae1 = "Raquel"
    val mae2 = "Bia"
    println(mae1 == mae2)

    // seguranca de dados - Nullable e Elvis

    // permitir que preencha com texto ou nao (null)
    var house: String? = "Mogi"
    house = null
    println(house)

    var addressBia: String? = "Rua A"
    val streetNumber = addressBia?.length
    val qtdDeCaracteres = if (addressBia != null) addressBia.length else 0
    println(qtdDeCaracteres)

    // operador Elvis
    val muitos = addressBia?.length ?: 0
    var nome2: String? = null
    // !! - forca ler
    println(nome2!!.length) // <- AQUI VAI DAR ERRO.

}