package co.tiagoaguiar.course.instagram.common.model

import android.net.Uri

/*
    user do firebase
    -> precisam estar inicializados
    -> os atributos precisam ser escritos iguais aos que estão no firebase
 */
data class User(
  val uuid: String? = null,
  val name: String? = null,
  val email: String? = null,
  val photoUrl: String? = null,
  val postCount: Int = 0,
  val followers: Int = 0,
  val following: Int = 0,
)