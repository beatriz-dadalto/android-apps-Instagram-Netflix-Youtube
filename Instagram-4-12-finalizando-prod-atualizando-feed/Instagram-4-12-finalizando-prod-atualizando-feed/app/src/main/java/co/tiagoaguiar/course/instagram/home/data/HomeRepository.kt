package co.tiagoaguiar.course.instagram.home.data

import co.tiagoaguiar.course.instagram.common.base.RequestCallback
import co.tiagoaguiar.course.instagram.common.model.Post

class HomeRepository(private val dataSourceFactory: HomeDataSourceFactory) {

  fun clearCache() {
    val localDataSource = dataSourceFactory.createLocalDataSource()
    localDataSource.putFeed(null)
  }

  fun fetchFeed(callback: RequestCallback<List<Post>>) {
    val localDataSource = dataSourceFactory.createLocalDataSource()
    val userId = localDataSource.fetchSession()

    val dataSource = dataSourceFactory.createFromFeed()

    dataSource.fetchFeed(userId, object : RequestCallback<List<Post>> {
      override fun onSuccess(data: List<Post>) {
        localDataSource.putFeed(data)
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

  fun logout() {
    dataSourceFactory.createRemoteDataSource().logout()
  }

}