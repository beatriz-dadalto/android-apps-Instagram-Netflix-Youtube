package co.tiagoaguiar.course.instagram.common.model

import java.util.UUID

object Database {

    // essas estruturas de dados são como se fossem tabelas no banco de dados
    val usersAuth = hashSetOf<UserAuth>()
    val photos = hashSetOf<Photo>()
    val posts = hashMapOf<String, MutableSet<Post>>() // para cada id terá uma coleção de posts
    val feeds = hashMapOf<String, MutableSet<Post>>() // para cada user terá os posts
    val followers = hashMapOf<String, Set<String>>() // um user tem N followers

    // uma vez autenticado guarda a referencia para utilizar no app quando precisar
    var sessionAuth: UserAuth? = null


    // simulação de usuários
    init {
        val userA = UserAuth(UUID.randomUUID().toString(), "UserA", "userA@gmail.com", "12345678")
        val userB = UserAuth(UUID.randomUUID().toString(), "UserB", "userB@gmail.com", "87654321")

        usersAuth.add(userA)
        usersAuth.add(userB)

        // criando as tabelas
        followers[userA.uuid] = hashSetOf()
        posts[userA.uuid] = hashSetOf()
        feeds[userA.uuid] = hashSetOf()

        followers[userA.uuid] = hashSetOf()
        posts[userA.uuid] = hashSetOf()
        feeds[userA.uuid] = hashSetOf()

        sessionAuth = usersAuth.first()
    }
}