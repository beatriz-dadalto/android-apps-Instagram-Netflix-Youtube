package classeabstrata

fun main() {
    val nicole = Musico("Nicole Mitchell")
    val kiko = Musico("Kiko Loureiro")
    val viktoria = Musico("Viktoria Mullova")

    val guitarra = Guitarra("Fender")
    tocar(guitarra, kiko)
    println("------------")
    val flauta = Flauta("Flauta X")
    tocar(flauta, nicole)
    println("------------")
    val violino = Violino("Violino Y")
    println("Violino cordas: ${violino.cordas}")
    tocar(violino, viktoria)

    println("-----Sobrescrita de funcoes abstratas -------")

    println("--BTN--")
    val btn = Btn("Salvar", 0xFF0000, Pair(20, 30))
    btn.render()
    println("--Link--")
    val link = Link("Entrar", Pair(40, 50))
    link.render()

}

fun tocar(instrumento: Instrumento, musico: Musico) {
    instrumento.tocar(musico)
}


// Sobrescrita de FUNCOES abstratas
abstract class Component {
    abstract fun position() : Pair<Int, Int>

    open fun render() {
        println("Desenhando a tela ${position().first} | ${position().second}")
    }
}

abstract class Text(val text: String) : Component() {
    override fun render() {
        super.render()
        println("Desenhando o texto: $text")
    }
}

class Btn(text: String, val backgroundColor: Int, val pos: Pair<Int, Int>) : Text(text) {
    override fun position(): Pair<Int, Int> {
        return pos
    }

    override fun render() {
        super.render()
        println("Pintando o fundo do btn com a cor $backgroundColor")
    }
}

class Link(text: String, val pos: Pair<Int, Int>) : Text(text) {
    override fun position(): Pair<Int, Int> {
        return pos
    }
}