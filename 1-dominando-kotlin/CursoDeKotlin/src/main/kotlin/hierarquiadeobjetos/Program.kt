package hierarquiadeobjetos

fun main() {
    val obj = getObject(1)
    if (obj is String) println(obj.length)
    if (obj is Int) println(obj.plus(10))


    val cast = obj as? String
    println("length: ${cast?.length}")
}

fun getObject(value: Int): Any {
    return when(value) {
        1 -> 1
        2 -> "Aula"
        3 -> true
        else -> 1.0
    }
}