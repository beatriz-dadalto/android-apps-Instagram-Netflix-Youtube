package co.tiagoaguiar.course.instagram.register.data

/*
    Responsável por tomar decisão do que vai fazer com os
    dados que chegaram e com os dados de resposta
    RegisterEmailDataSource eh interface - instruções necessarias
 */
class RegisterRepository(private val dataSource: RegisterDataSource) {

    fun create(email: String, callback: RegisterCallback) {
        dataSource.create(email, callback)
    }

    fun create(email: String, name: String, password: String, callback: RegisterCallback) {
        dataSource.create(email, name, password, callback)
    }
}