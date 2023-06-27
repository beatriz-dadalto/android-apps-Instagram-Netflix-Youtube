package colecoesdedados

fun main() {
    val numbers = listOf(1, 2, 3, 4, 5, 6)
    val numbers2 = listOf(1, 2, 5, 10, 12, 16)
    // union retorna um Set
    val joinedList = numbers.union(numbers2)
    joinedList.forEachIndexed { index, i ->  println("index: $index, elemento: $i") }
}