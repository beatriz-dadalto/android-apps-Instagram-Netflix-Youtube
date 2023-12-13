package co.tiagoaguiar.course.instagram.profile.data

import co.tiagoaguiar.course.instagram.common.base.RequestCallback
import co.tiagoaguiar.course.instagram.common.model.Post
import co.tiagoaguiar.course.instagram.common.model.UserAuth

interface ProfileDataSource {

    fun fetchUserProfile(userUUID: String, callback: RequestCallback<Pair<UserAuth, Boolean?>>)
    fun fetchuserPosts(userUUID: String, callback: RequestCallback<List<Post>>)
    fun fetchSession(): UserAuth {
        // toda vez que tentar chamar vai dar crash no app pq esse metodo nao precisa buscar sessão quando for REMOTE
        throw UnsupportedOperationException()
    }

    fun putUser(response: Pair<UserAuth, Boolean?>) {
        // toda vez que tentar chamar vai dar crash no app pq esse metodo nao precisa armazenar user quando for REMOTE
        throw UnsupportedOperationException()
    }

    fun putPosts(response: List<Post>?) {
        // toda vez que tentar chamar vai dar crash no app pq esse metodo nao precisa armazenar posts quando for REMOTE
        throw UnsupportedOperationException()
    }
}