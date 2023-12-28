package co.tiagoaguiar.course.instagram.search.data

import android.os.Handler
import android.os.Looper
import co.tiagoaguiar.course.instagram.common.base.RequestCallback
import co.tiagoaguiar.course.instagram.common.model.Database
import co.tiagoaguiar.course.instagram.common.model.User

class SearchFakeRemoteDataSource : SearchDataSource {

    override fun fetchUsers(name: String, callback: RequestCallback<List<User>>) {
        Handler(Looper.getMainLooper()).postDelayed({
            val users = Database.usersAuth.filter { user ->
                user.name.lowercase()
                    .startsWith(name.lowercase()) && user.uuid != Database.sessionAuth!!.uuid
            }

//            callback.onSuccess(users.toList())
            callback.onComplete()
        }, 2000)
    }

}