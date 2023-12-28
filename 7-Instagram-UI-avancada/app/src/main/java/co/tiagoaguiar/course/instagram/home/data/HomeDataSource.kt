package co.tiagoaguiar.course.instagram.home.data

import co.tiagoaguiar.course.instagram.common.base.RequestCallback
import co.tiagoaguiar.course.instagram.common.model.Post
import co.tiagoaguiar.course.instagram.common.model.UserAuth

interface HomeDataSource {

    fun fetchFeed(userUUID: String, callback: RequestCallback<List<Post>>)

    fun fetchSession(): String {
        // toda vez que tentar chamar vai dar crash no app pq esse metodo nao precisa buscar sessão quando for REMOTE
        throw UnsupportedOperationException()
    }

    fun putFeed(response: List<Post>?) {
        // toda vez que tentar chamar vai dar crash no app pq esse metodo nao precisa armazenar o feed quando for REMOTE
        throw UnsupportedOperationException()
    }
}