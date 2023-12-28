package co.tiagoaguiar.course.instagram.profile.data

import android.os.Handler
import android.os.Looper
import co.tiagoaguiar.course.instagram.common.base.RequestCallback
import co.tiagoaguiar.course.instagram.common.model.Database
import co.tiagoaguiar.course.instagram.common.model.Post
import co.tiagoaguiar.course.instagram.common.model.User

class ProfileFakeRemoteDataSource : ProfileDataSource {
    override fun fetchUserProfile(
        userUUID: String,
        callback: RequestCallback<Pair<User, Boolean?>>
    ) {
        Handler(Looper.getMainLooper()).postDelayed({
            val userAuth = Database.usersAuth.firstOrNull { userUUID == it.uuid }

            if (userAuth != null) {
                if (userAuth == Database.sessionAuth) {
                    //callback.onSuccess(Pair(userAuth, null))
                } else {
                    // buscando um user do perfil
                    val followings = Database.followers[Database.sessionAuth!!.uuid]
                    // destinyUser != null = estou seguindo
                    val destinyUser = followings?.firstOrNull { it == userUUID }

                    val following = destinyUser != null
                    //callback.onSuccess(Pair(userAuth, following))
                }
            } else {
                callback.onFailure("usuário não encontrado")
            }

            callback.onComplete()
        }, 2000)
    }

    override fun fetchuserPosts(userUUID: String, callback: RequestCallback<List<Post>>) {
        Handler(Looper.getMainLooper()).postDelayed({
            val posts = Database.posts[userUUID]
            callback.onSuccess(posts?.toList() ?: emptyList())
            callback.onComplete()
        }, 2000)
    }

    override fun followUser(
        userUUID: String,
        isFollow: Boolean,
        callback: RequestCallback<Boolean>
    ) {
        Handler(Looper.getMainLooper()).postDelayed({
            var followers = Database.followers[Database.sessionAuth!!.uuid]

            if (followers == null) {
                followers = mutableSetOf()
                Database.followers[Database.sessionAuth!!.uuid] = followers
            }

            if (isFollow) {
                Database.followers[Database.sessionAuth!!.uuid]!!.add(userUUID)
            } else {
                Database.followers[Database.sessionAuth!!.uuid]!!.remove(userUUID)
            }

            callback.onSuccess(true)
            callback.onComplete()
        }, 500)
    }

}