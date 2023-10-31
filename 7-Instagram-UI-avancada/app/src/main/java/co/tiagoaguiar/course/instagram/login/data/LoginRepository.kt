package co.tiagoaguiar.course.instagram.login.data

/*
    Responsável por tomar decisão do que vai fazer com os
    dados que chegaram e com os dados de resposta
    LoginDataSource eh interface - instruções necessarias
 */
class LoginRepository(private val dataSource: LoginDataSource) {

    fun login(email: String, password: String, callback: LoginCallback) {
        dataSource.login(email, password, callback)
    }
}