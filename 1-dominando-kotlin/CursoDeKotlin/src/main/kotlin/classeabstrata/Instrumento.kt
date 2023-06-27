package classeabstrata

/*
    1) a classe filha é obrigada a implementar o comportamento
    2) classes abstratas podem ter ou não fun abstract
    3) como deixar o codigo mais flexivel e generico com abstract class?
      R: Fazer com que a classe mãe consiga usar o COMPORTAMENTO da classe filha

 */

class Musico(var nome: String) {

}

// classe vó
abstract class Instrumento(var nome: String) {
    /*
        a assinatura da fun eh a mesma (afinar) -> abstrato
        soh que, o comportamento de afinar eh diferente -> concreto
     */
    abstract fun afinar(): Boolean

    fun tocar(musico: Musico) {
        // quando executar tocar() ja vai chamar a fun afinar()
        if (afinar()) {
            println("Tocando $nome, por ${musico.nome}")
        } else {
            println("Por favor ${musico.nome}, afine seu $nome antes de toca-lo!")
        }
    }
}

// classe mãe
abstract class InstrumentoDeCorda(nome: String, var cordas: Int) : Instrumento(nome) {

}

// classe filha
class Guitarra(nome: String) : InstrumentoDeCorda(nome, 6) {

    override fun afinar(): Boolean {
        println("Afinação em E (Mi)")
        return true
    }
}

class Violino(nome: String) : InstrumentoDeCorda(nome, 4) {
    override fun afinar(): Boolean {
        println("Afinação em G (Sol)")
        return false
    }
}

class Flauta(nome: String) : Instrumento(nome) {
    override fun afinar(): Boolean {
        println("Afinação em C (Dó)")
        return true
    }
}
