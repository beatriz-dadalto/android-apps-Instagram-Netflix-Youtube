package polimorfismo

import java.io.FileWriter
import java.io.IOException

/*
Abstract -> Quando você quer reaproveitar comportamentos ou propriedades da classe superior.
Por exemplo, você está criando uma classe Aluno e uma classe Professor, as duas classes tem algo em comum como o "nome".
Logo, você declara o nome na super classe que poderia ser Pessoa.

Interface -> Quando você quer que a sua classe seja tratada como um "outro tipo" tendo multiplos comportamentos para
serem implementados.

Por exemplo, você tem um botão e irá delegar a responsabilidade do evento "clicou" para uma classe que implementa a
interface "Clickable" (clicavel). Logo, se voce tem uma classe XYZ, você pode dizer que XYZ agora é "clicável" se ela
implementar a interface. Em outras palavras, a interface é para você assinar um novo comportamento para a sua classe.
Para que ela possa se comunicar com as demais via interface.
 */

fun main() {

    val userA = Usuario(buscarSistemaDeLog())
    userA.criarPublicacao()

    val userB = Usuario(buscarSistemaDeLog())
    userB.criarPublicacao()

}

interface LoggerGenerico {
    fun print(message: String)
}

class SistemaConsole: LoggerGenerico {
    override fun print(message: String) {
        println(message)
    }
}

class SistemaDeArquivo: LoggerGenerico {
    override fun print(message: String) {
        val path = "/home/beatriz/aula.txt"

        try {
            val fw = FileWriter(path, true)
            fw.write(message)
            fw.write("\n")
            fw.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}

class Usuario(val logger: LoggerGenerico) {
    init {
        log("Usuario criado!")
    }

    fun criarPublicacao() {
        log("Novo post criado!")
    }

    private fun log(mensagem: String) {
        logger.print(mensagem)
    }
}

fun buscarSistemaDeLog() : LoggerGenerico {
    return SistemaDeArquivo()
}