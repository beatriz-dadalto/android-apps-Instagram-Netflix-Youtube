package exceptions

fun main() {
    val objeto = getObject(2)

    val convertido = objeto as String
    println("length: ${convertido.length}")

    // Exceptions
    divide("Dez", "Cinco")

    // Lancando excecao
    showView()
}

fun getObject(value: Int): Any {
    return when(value) {
        1 -> 1
        2 -> "Aula"
        3 -> true
        else -> 1.0
    }
}

fun divide(x: String, y: String) {
    try {
        val a = Integer.parseInt(x)
        val b = Integer.parseInt(y)
        println(a / b)
    } catch (e: Exception) {
        println("Error -> ${e.message}")
    } catch (e: ArithmeticException) {
        println("Error: ${e.message}")
    }
}

// Lancando excecao
fun applyDiscount(price: Double, value: Int): Double {
    if (value > 50) {
        throw IllegalArgumentException("Desconto muito alto!")
    }
    val discount = value * price / 100
    return price - discount
}

fun showView() {
    try {
        val res = applyDiscount(100.0, 54)
        println(res)
    } catch (e: IllegalArgumentException) {
        println(e.message)
    }
}