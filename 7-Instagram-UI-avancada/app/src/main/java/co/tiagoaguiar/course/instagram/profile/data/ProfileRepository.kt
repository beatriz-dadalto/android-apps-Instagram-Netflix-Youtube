package co.tiagoaguiar.course.instagram.profile.data

import co.tiagoaguiar.course.instagram.common.base.RequestCallback
import co.tiagoaguiar.course.instagram.common.model.Post
import co.tiagoaguiar.course.instagram.common.model.UserAuth

/*
     padrao factory pra decidir onde vai buscar os dados
     se vai buscar no local ou remoto
 */
class ProfileRepository(private val dataSourceFactory: ProfileDataSourceFactory) {

    fun clearCache() {
        val localDataSource = dataSourceFactory.createLocalDataSource()
        localDataSource.putPosts(null)
    }

    fun fetchUserProfile(callback: RequestCallback<UserAuth>) {
        val localDataSource = dataSourceFactory.createLocalDataSource()
        val userAuth = localDataSource.fetchSession()

        // ou LOCAL data source
        // ou REMOTE data source
        val dataSource = dataSourceFactory.createFromUser()

        dataSource.fetchUserProfile(userAuth.uuid, object : RequestCallback<UserAuth> {
            override fun onSuccess(data: UserAuth) {
                localDataSource.putUser(data)
                callback.onSuccess(data)
            }

            override fun onFailure(message: String) {
                callback.onFailure(message)
            }

            override fun onComplete() {
                callback.onComplete()
            }

        })
    }

    fun fetchUserPosts(callback: RequestCallback<List<Post>>) {
        val localDataSource = dataSourceFactory.createLocalDataSource()
        val userAuth = localDataSource.fetchSession()

        // ou LOCAL data source
        // ou REMOTE data source
        val dataSource = dataSourceFactory.createFromPosts()

        dataSource.fetchuserPosts(userAuth.uuid, object : RequestCallback<List<Post>> {
            override fun onSuccess(data: List<Post>) {
                localDataSource.putPosts(data)
                callback.onSuccess(data)
            }

            override fun onFailure(message: String) {
                callback.onFailure(message)
            }

            override fun onComplete() {
                callback.onComplete()
            }
        })
    }
}