package co.tiagoaguiar.course.instagram.common.model

import java.util.UUID

object Database {
    val usersAuth = hashSetOf<UserAuth>()

    // uma vez autenticado guarda a referencia para utilizar no app quando precisar
    var sessionAuth: UserAuth? = null

    init {
        usersAuth.add(UserAuth(UUID.randomUUID().toString(), "userA@gmail.com", "12345678"))
        usersAuth.add(UserAuth(UUID.randomUUID().toString(), "userB@gmail.com", "87654321"))
    }
}