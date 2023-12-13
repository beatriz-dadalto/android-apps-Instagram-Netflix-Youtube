package co.tiagoaguiar.course.instagram.profile.data

import co.tiagoaguiar.course.instagram.common.base.Cache
import co.tiagoaguiar.course.instagram.common.model.Post
import co.tiagoaguiar.course.instagram.common.model.UserAuth

/*
    ProfileCache -> classe generica para saber qual tipo de cache que precisa fazer
 */
class ProfileDataSourceFactory(
    private val profileCache: Cache<UserAuth>,
    private val postsCache: Cache<List<Post>>
) {

    fun createLocalDataSource(): ProfileDataSource {
        return ProfileLocalDataSource(profileCache, postsCache)
    }

    fun createFromUser(uuid: String?): ProfileDataSource {
        if (uuid != null) return ProfileFakeRemoteDataSource()

        return if (profileCache.isCached()) {
            ProfileLocalDataSource(profileCache, postsCache)
        } else {
            ProfileFakeRemoteDataSource()
        }
    }

    fun createFromPosts(uuid: String?): ProfileDataSource {
        if (uuid != null) return ProfileFakeRemoteDataSource()

        if (postsCache.isCached()) {
            return ProfileLocalDataSource(profileCache, postsCache)
        }
        return ProfileFakeRemoteDataSource()
    }
}