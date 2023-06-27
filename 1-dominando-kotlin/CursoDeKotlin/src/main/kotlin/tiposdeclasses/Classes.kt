package tiposdeclasses

class Musician {
    var style: String = "Rock"
    fun desc() = println("Meu estilo é $style")

    // Nested Class
    class Guitar {
        var strings = 6
        fun desc() = println("Tocando uma guitarra com $strings cordas.")
    }

    // Inner Class
    inner class Sax {
        var family = "Saxofone soprano"
        fun desc() = println("Família $family mas $style é vida")
    }
}
