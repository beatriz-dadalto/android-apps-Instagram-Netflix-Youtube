package moduloseinternalclasses

/*
    internal: a classe fica disponivel apenas nesse arquivo
    - restringir a estrutura do codigo para q pessoas de fora nao modifiquem o comportamento

 */

internal class Encordoamento(val numeroDeCorda: Int, val marca: String) {}

open class Instrumento(var cor: String) {

    private lateinit var encordoamento: Encordoamento

    fun console() {
        println("Esse instrumento é da cor $cor e tem ${encordoamento.numeroDeCorda} cordas")
    }

    // a classe Instrumento vai gerenciar a classe Encordoamento
    fun addEncordoamento(num: Int, marca: String) {
        encordoamento = Encordoamento(num, marca)
    }
}

class Guitarra(cor: String) : Instrumento(cor) {
    init {
        addEncordoamento(6, "NIG")
    }
}
