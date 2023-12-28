package co.tiagoaguiar.course.instagram.profile.data

import co.tiagoaguiar.course.instagram.common.base.Cache
import co.tiagoaguiar.course.instagram.common.model.Post
import co.tiagoaguiar.course.instagram.common.model.User

/*
    ProfileCache -> classe generica para saber qual tipo de cache que precisa fazer
 */
class ProfileDataSourceFactory(
    private val profileCache: Cache<Pair<User, Boolean?>>,
    private val postsCache: Cache<List<Post>>
) {

    fun createLocalDataSource(): ProfileDataSource {
        return ProfileLocalDataSource(profileCache, postsCache)
    }

    fun createFromUser(uuid: String?): ProfileDataSource {
        if (uuid != null) return createRemoteDataSource()

        return if (profileCache.isCached()) {
            ProfileLocalDataSource(profileCache, postsCache)
        } else {
            createRemoteDataSource()
        }
    }

    fun createFromPosts(uuid: String?): ProfileDataSource {
        if (uuid != null) return createRemoteDataSource()

        if (postsCache.isCached()) {
            return ProfileLocalDataSource(profileCache, postsCache)
        }
        return createRemoteDataSource()
    }

    fun createRemoteDataSource(): ProfileDataSource {
        return FirebaseProfileDataSource()
    }
}