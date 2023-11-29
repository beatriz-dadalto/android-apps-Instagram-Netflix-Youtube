package co.tiagoaguiar.course.instagram.profile.data

import co.tiagoaguiar.course.instagram.common.model.Post
import co.tiagoaguiar.course.instagram.common.model.UserAuth

/*
    ProfileCache -> classe generica para saber qual tipo de cache que precisa fazer
 */
class ProfileDataSourceFactory(
    private val profileCache: ProfileCache<UserAuth>,
    private val postsCache: ProfileCache<List<Post>>
) {

    fun createLocalDataSource(): ProfileDataSource {
        return ProfileLocalDataSource(profileCache, postsCache)
    }

    fun createFromUser(): ProfileDataSource {
        return if (profileCache.isCached()) {
            ProfileLocalDataSource(profileCache, postsCache)
        } else {
            ProfileFakeRemoteDataSource()
        }
    }

    fun createFromPosts(): ProfileDataSource {
        if (postsCache.isCached()) {
            return ProfileLocalDataSource(profileCache, postsCache)
        }
        return ProfileFakeRemoteDataSource()
    }
}