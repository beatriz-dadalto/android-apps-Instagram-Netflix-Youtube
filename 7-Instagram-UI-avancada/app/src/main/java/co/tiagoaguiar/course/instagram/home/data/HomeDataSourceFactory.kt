package co.tiagoaguiar.course.instagram.home.data

import co.tiagoaguiar.course.instagram.common.base.Cache
import co.tiagoaguiar.course.instagram.common.model.Post

/*
    Cache -> classe generica para saber qual tipo de cache que precisa fazer
 */
class HomeDataSourceFactory(
    private val feedCache: Cache<List<Post>>
) {

    fun createLocalDataSource(): HomeDataSource {
        return HomeLocalDataSource(feedCache)
    }

    fun createFromFeed(): HomeDataSource {
        return if (feedCache.isCached()) {
            HomeLocalDataSource(feedCache)
        } else {
            HomeFakeRemoteDataSource()
        }
    }

}