package co.tiagoaguiar.course.instagram.common.model

import java.util.UUID

object Database {

    // essas estruturas de dados são como se fossem tabelas no banco de dados
    val usersAuth = hashSetOf<UserAuth>()
    val photos = hashSetOf<Photo>()
    val posts = hashMapOf<String, Set<Post>>() // para cada id terá uma coleção de posts
    val feeds = hashMapOf<String, Set<Post>>() // para cada user terá os posts

    // uma vez autenticado guarda a referencia para utilizar no app quando precisar
    var sessionAuth: UserAuth? = null

    init {
        usersAuth.add(UserAuth(UUID.randomUUID().toString(), "UserA", "userA@gmail.com", "12345678"))
        usersAuth.add(UserAuth(UUID.randomUUID().toString(), "UserB", "userB@gmail.com", "87654321"))

        sessionAuth = usersAuth.first()
    }
}