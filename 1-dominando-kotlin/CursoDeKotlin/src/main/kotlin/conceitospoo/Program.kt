package conceitospoo

fun main() {

    // HERANCA
    val p = Professor("Girafales", "Rua A")
    println(p.descricao())

    val a = Aluno(123, "Bia", "Rua B")
    println(a.descricao())
}

// HERANÇA -> objetoX eh objetoY (herda as props)

// SUPER Classe
open class Pessoa(var nome: String, var endereco: String) {
    protected var acessoBiblioteca = false
    fun descricao() = "Meu nome é $nome e meu endereço é $endereco. Nível de acesso: $acessoBiblioteca"
}
class Professor(nome: String, endereco: String) : Pessoa(nome, endereco){
    init {
        acessoBiblioteca = true
    }
}

class Aluno(var matriculaId: Int, nome: String, endereco: String) : Pessoa(nome, endereco) {

}