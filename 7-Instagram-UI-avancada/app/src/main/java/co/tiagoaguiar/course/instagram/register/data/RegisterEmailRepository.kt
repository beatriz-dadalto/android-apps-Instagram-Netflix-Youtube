package co.tiagoaguiar.course.instagram.register.data

/*
    Responsável por tomar decisão do que vai fazer com os
    dados que chegaram e com os dados de resposta
    RegisterEmailDataSource eh interface - instruções necessarias
 */
class RegisterEmailRepository(private val dataSource: RegisterEmailDataSource) {

    fun create(email: String, callback: RegisterEmailCallback) {
        dataSource.create(email, callback)
    }
}