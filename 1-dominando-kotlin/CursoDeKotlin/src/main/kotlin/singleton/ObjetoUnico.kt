package singleton

import oop.User

object Database {
    // criar um unico objeto (Singleton) use OBJECT inves de CLASS
    var name = "Desconhecido"
    var userLogged: User? = null
}