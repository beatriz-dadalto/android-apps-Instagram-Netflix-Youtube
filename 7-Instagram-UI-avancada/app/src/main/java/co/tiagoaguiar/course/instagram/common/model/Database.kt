package co.tiagoaguiar.course.instagram.common.model

import android.net.Uri
import java.io.File
import java.util.UUID

object Database {

    // essas estruturas de dados são como se fossem tabelas no banco de dados
    val usersAuth = mutableListOf<UserAuth>()
    val posts = hashMapOf<String, MutableSet<Post>>() // para cada id terá uma coleção de posts
    val feeds = hashMapOf<String, MutableSet<Post>>() // para cada user terá os posts
    val followers = hashMapOf<String, MutableSet<String>>() // um user tem N followers

    // uma vez autenticado guarda a referencia para utilizar no app quando precisar
    var sessionAuth: UserAuth? = null


    // simulação de usuários
    init {
        val userA = UserAuth(
            UUID.randomUUID().toString(),
            "UserA",
            "userA@gmail.com",
            "12345678",
            Uri.fromFile(File("/storage/emulated/0/Android/media/co.tiagoaguiar.course.instagram/Instagram/2023-12-06-13-29-42-293.jpg"))
        )
        val userB = UserAuth(
            UUID.randomUUID().toString(),
            "UserB",
            "userB@gmail.com",
            "87654321",
            Uri.fromFile(File("/storage/emulated/0/Android/media/co.tiagoaguiar.course.instagram/Instagram/2023-12-06-13-29-42-293.jpg"))
        )

        usersAuth.add(userA)
        usersAuth.add(userB)

        // criando as tabelas
        followers[userA.uuid] = hashSetOf()
        posts[userA.uuid] = hashSetOf()
        feeds[userA.uuid] = hashSetOf()

        followers[userB.uuid] = hashSetOf()
        posts[userB.uuid] = hashSetOf()
        feeds[userB.uuid] = hashSetOf()

//        feeds[userA.uuid]?.addAll(
//            arrayListOf(
//                Post(
//                    UUID.randomUUID().toString(),
//                    Uri.fromFile(File("/storage/emulated/0/Android/media/co.tiagoaguiar.course.instagram/Instagram/2023-12-06-13-29-42-293.jpg")),
//                    "desc",
//                    System.currentTimeMillis(),
//                    userA
//                ),
//                Post(
//                    UUID.randomUUID().toString(),
//                    Uri.fromFile(File("/storage/emulated/0/Android/media/co.tiagoaguiar.course.instagram/Instagram/2023-12-06-13-29-42-293.jpg")),
//                    "desc",
//                    System.currentTimeMillis(),
//                    userA
//                ),
//                Post(
//                    UUID.randomUUID().toString(),
//                    Uri.fromFile(File("/storage/emulated/0/Android/media/co.tiagoaguiar.course.instagram/Instagram/2023-12-06-13-29-42-293.jpg")),
//                    "desc",
//                    System.currentTimeMillis(),
//                    userA
//                ),
//                Post(
//                    UUID.randomUUID().toString(),
//                    Uri.fromFile(File("/storage/emulated/0/Android/media/co.tiagoaguiar.course.instagram/Instagram/2023-12-06-13-29-42-293.jpg")),
//                    "desc",
//                    System.currentTimeMillis(),
//                    userA
//                )
//            )
//        )
//
//        feeds[userA.uuid]?.toList()?.let {
//            feeds[userB.uuid]?.addAll(it)
//        }

        // users para fazer pesquisa na barra de pesquisa
        for (i in 0..30) {
            val user = UserAuth(
                UUID.randomUUID().toString(),
                "User$i",
                "user$i@gmail.com",
                "123123123",
                null
            )
            usersAuth.add(user)
        }

        // sessionAuth = usersAuth.first()

        // followers[sessionAuth!!.uuid]?.add(usersAuth[2].uuid)
    }
}