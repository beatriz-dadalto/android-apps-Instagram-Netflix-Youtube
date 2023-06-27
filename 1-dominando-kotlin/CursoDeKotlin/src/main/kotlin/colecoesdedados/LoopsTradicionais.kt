package colecoesdedados

fun main() {
    val numbers = listOf(1, 2, 3, 4, 5)
    val numbers2 = listOf(1, 2, 5, 12, 16)

    // Tradicionais
    for (number in numbers) {
        println(number)
    }

    // RANGE
    println("numbers - until")
    for (index in 0 until 3) {
        println("index: $index, elemento: ${numbers[index]}")
    }

    // RANGE com inclusao
    println("numbers2 - ..")
    for (index in 0 .. 3) {
        println("index: $index, elemento: ${numbers2[index]}")
    }

    println("For com Step")
    for (i in 0 until 20 step 2) {
        println("N#$i")
    }

    println("Decrementando")
    for (i in 20 downTo 0 step 3) {
        println("Number: $i")
    }

    println("WHILE")
    var i = 0
    while (i < 7) {
        println(i)
        i++
    }
}