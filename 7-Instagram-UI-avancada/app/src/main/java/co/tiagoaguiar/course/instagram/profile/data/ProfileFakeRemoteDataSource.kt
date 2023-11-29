package co.tiagoaguiar.course.instagram.profile.data

import android.os.Handler
import android.os.Looper
import co.tiagoaguiar.course.instagram.common.base.RequestCallback
import co.tiagoaguiar.course.instagram.common.model.Database
import co.tiagoaguiar.course.instagram.common.model.Post
import co.tiagoaguiar.course.instagram.common.model.UserAuth

class ProfileFakeRemoteDataSource : ProfileDataSource {
    override fun fetchUserProfile(userUUID: String, callback: RequestCallback<UserAuth>) {
        Handler(Looper.getMainLooper()).postDelayed({
            val userAuth = Database.usersAuth.firstOrNull { userUUID == it.uuid }

            if (userAuth != null) {
                callback.onSuccess(userAuth)
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

}