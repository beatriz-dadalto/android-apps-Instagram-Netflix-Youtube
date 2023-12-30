package co.tiagoaguiar.course.instagram.common.model

import android.net.Uri
import java.io.File
import java.util.*

object Database {

  val usersAuth = mutableListOf<UserAuth>()
  val posts = hashMapOf<String, MutableSet<Post>>()
  val feeds = hashMapOf<String, MutableSet<Post>>()
  val followers = hashMapOf<String, MutableSet<String>>()

  var sessionAuth: UserAuth? = null

  init {
     val userA = UserAuth(UUID.randomUUID().toString(), "UserA", "userA@gmail.com", "12345678", null)

    // val userB = UserAuth(UUID.randomUUID().toString(), "UserB", "userB@gmail.com", "87654321", Uri.fromFile(
    // File("/storage/emulated/0/Android/media/co.tiagoaguiar.course.instagram/Instagram/2021-09-06-17-48-17-538.jpg")))

    usersAuth.add(userA)
    // usersAuth.add(userB)

    // followers[userA.uuid] = hashSetOf()
    // posts[userA.uuid] = hashSetOf()
    // feeds[userA.uuid] = hashSetOf()

    // followers[userB.uuid] = hashSetOf()
    // posts[userB.uuid] = hashSetOf()
    // feeds[userB.uuid] = hashSetOf()

    // for(i in 0..30) {
    //   val user = UserAuth(UUID.randomUUID().toString(), "User$i", "user$i@gmail.com", "123123123", null)
    //   usersAuth.add(user)
    // }

    // sessionAuth = usersAuth.first()

    // followers[sessionAuth!!.uuid]?.add(usersAuth[2].uuid)
  }

}