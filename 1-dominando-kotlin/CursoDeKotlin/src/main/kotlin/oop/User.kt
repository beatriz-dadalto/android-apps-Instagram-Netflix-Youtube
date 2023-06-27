package oop

/*
    JEITO 1 - escrevendo a palavra constructor
    class User constructor(var name: String = "")
 */

// JEITO 2 - sem a palavra constructor
// Sobrecarga de construtores

class User(var name: String = "", var isAdmin: Boolean) {
    // DRY - Dont repeat yourself
    // Tratar tudo como um objeto

    // lateinit = vai ter valor no futuro. assim nao preciso declarar como NULLABLE
    lateinit var lastName: String

    var count = 0 // prop de OBJETO

    // COMPANION OBJECT - props e comportamentos da classe - Naaoooo do obj.
    companion object {
        /*
            const - em tempo de COMPILACAO
            val - em tempo de EXECUCAO
         */
        const val MAX_LENGTH_NAME = 8
        var counter = 1 // prop da CLASSE
        var name = "PROP da CLASSE"
         fun resetCount() {
             counter = 0
         }
    }

    // INIT é parecido com o construtor mas executa sempre depois do construtor padrão.
    // os construtores executam em pilha (LIFO – Last in ,first out), do padrão para os demais.
    // INIT irá executar na ordem que foram escritos
    init {
        println(count++)
        println("executou o init-2 $name")
    }

    init {
        println("executou o init-1")
    }

    init {
        println("executou o init-3")
    }

    constructor(name: String): this(name, isAdmin = false) {
        println("Executou o construtor 2")
    }

    constructor(): this(name = "Desconhecido", isAdmin = false) {
        println("Executou o construtor 3")
    }

    fun output() = println("Meu nome é $name $lastName, sou admin: $isAdmin")

    fun printUpperCase() {
        println("Olá, ${name.uppercase()}")
    }

    fun updateName(newName: String) {
        name = "$newName. Nome atualizado com sucesso!"
    }

    fun getNameLength(): Int {
        return name.length
    }

    override fun toString(): String {
        return "name=$name isAdmin=$isAdmin"
    }
}