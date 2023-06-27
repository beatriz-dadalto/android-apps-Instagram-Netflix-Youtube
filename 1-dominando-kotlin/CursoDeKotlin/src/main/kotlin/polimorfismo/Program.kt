package polimorfismo

fun main() {

    val musico1 = Musico("Tiago")
    musico1.playable = buscarINstrumento()
    musico1.startPlay()
}

class Musico(val nome: String) {

    lateinit var playable: Playable

    fun startPlay() {
        playable.play()
    }
}

interface Playable {
    fun play()
}

class Guitar: Playable {
    override fun play() {
        println("Tocando guitarra")
    }

}

class Drum: Playable {
    override fun play() {
        println("Tocando bateria")
    }
}

class Violin: Playable {
    override fun play() {
        println("Tocando violino")
    }
}

fun buscarINstrumento(): Playable {
    return Violin()
}