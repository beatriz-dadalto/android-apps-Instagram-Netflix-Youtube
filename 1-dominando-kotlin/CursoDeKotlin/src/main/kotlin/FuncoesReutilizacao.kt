fun main() {
    olaMundo()
    println(sum())
    // posso escrever o nome do parametro (name) ou deixar implicito mesmo.
    myName(name = "Beatriz") // explicito
    myName("Anny Caroline") // implicito
    println(myFavouriteBand("Turnstile"))
    dogName("Wolly") // declaracao com um unico corpo
    checkLogin(age = 25, name = "Catarina") // sobrecarga
    checkLogin(age = 53, name = "Alexandre", isAdmin = true) // sobrecarga
    nastyChild(name = "Sarah") // usando o valor DEFAULT
}

// Valor DEFAULT
fun nastyChild(name: String, isNasty: Boolean = false) {
    println("$name é uma criança peste? : $isNasty")
}

// sobrecarga - funcao com nomes iguais mas parametros diferentes
fun checkLogin(age: Int, name: String, isAdmin: Boolean = false) {
    println("Minha idade é $age e meu nome é $name, sou admin: $isAdmin")
}

fun checkLogin(age: Int, name: String) {
    println("Minha idade é $age e meu nome é $name")
}

// declaracao com um unico corpo
fun dogName(name: String) = println("$name")

fun olaMundo() {
    println("Olá, Mundo!")

    // roda apenas aqui. ESCOPO
    fun myCountry(country: String) {
        println(country)
    }

    myCountry("Brasil")
    myCountry("Australia")
}

fun sum(): Int {
    return 25 + 8
}

fun myName(name: String) {
    println("My name is $name")
}

fun myFavouriteBand(band: String): String {
    return "My actual favourite band is $band from Australia"
}