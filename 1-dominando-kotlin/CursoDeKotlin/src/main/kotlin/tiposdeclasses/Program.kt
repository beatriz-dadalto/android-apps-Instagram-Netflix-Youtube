package tiposdeclasses

import oop.User

fun main() {
    /*
        TIPOS DE CLASSES
        Nested Class
        Inner Class
        Enum Class
        Data Class
     */

    // Estrutura de dados simples. sem precisar criar Data Class
    // Pair eh um Data Class
    val coordenadas = Pair(10,"Bia")
    val userById = "visa" to 30 // JEITO 2 de usar o PAIR
    println(coordenadas.first)
    println(coordenadas.second)
    val triple = Triple(200,100,1)
    println(triple.third)

    // COPIAR um OBJETO. eh possível apenas de Data Class
    val userToCopy = ClasseDeDados("Carol", false, "De Oliveira")
    // modificar apenas a prop isAdmin
    val copiedUser = userToCopy.copy(isAdmin = true)

    // Desestruturação de Classe - eh possível apenas de Data Class
    val userX = ClasseDeDados(name = "Anny", lastName = "Galiati", isAdmin = false)
    val (name, lastName, isAdmin) = userX
    //Meu nome é Anny false. Sou admin: Galiati - por que? imprimiu ordem diferente?
    // porque o que importa é na ordem de declaração da classe. uhulll
    println("Meu nome é $name $lastName. Sou admin: $isAdmin")


    // Quando utilizar o Data Class VS Class? -  data class para estrutura de dados, um model
    val user1 = ClasseDeDados("Bia", false, "Dadalto")
    val user2 = ClasseDeDados("Bia", false, "Dadalto")
    println(user1)
    println(user2)
    // Data Class compara o conteudo, por isso, vai retornar true
    println("user1 eh igual a user2: ${user1 == user2}")


    println("---------")

    val kikoLoureiro = Musician()
    kikoLoureiro.desc()

    val guitar = Musician.Guitar()
    guitar.desc()

    val sax = kikoLoureiro.Sax()
    sax.desc()

    println("posição do enum: ${CreditCard.VISA.ordinal}")
    // codigo que busca em um servidor a bandeira baseado nos primeiros numeros do cartao
    val creditCardAPIName = "MASTER" // veio do servidor
    val card = CreditCard.valueOf(creditCardAPIName)

    if (card == CreditCard.VISA) {
        println("Dá um desconto!")
    } else {
        println("Avisa o usuário que o cartão VISA tem desconto!")
        println("Você está usando o cartão ${card.label} que tem desconto de: ${card.discountPercent}%")
    }

    // exemplo com WHEN
    when(card) {
        CreditCard.VISA -> println("ganhou desconto! uhulll")
        else -> println("Nenhum desconto aplicado!")
    }

    // RETORNOS ENCURTADOS
    val discount1 = when(card) {
        CreditCard.VISA -> "ganhou desconto! uhulll"
        CreditCard.MASTER -> "ganhou SUPER desconto!"
        else -> "Nenhum desconto aplicado!"
    }

    val discount2 = if (card == CreditCard.VISA) {
        println("entrou no if") // logicas dentro do bloco
        "desconto de 10%" // a ultima linha eh o RETURN
    } else {
        "desconto aplicado!"
    }



}

