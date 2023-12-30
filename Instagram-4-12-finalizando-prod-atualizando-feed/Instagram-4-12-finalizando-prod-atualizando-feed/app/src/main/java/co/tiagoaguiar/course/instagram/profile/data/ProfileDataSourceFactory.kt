package co.tiagoaguiar.course.instagram.profile.data

import co.tiagoaguiar.course.instagram.common.base.Cache
import co.tiagoaguiar.course.instagram.common.model.Post
import co.tiagoaguiar.course.instagram.common.model.User
import co.tiagoaguiar.course.instagram.common.model.UserAuth

class ProfileDataSourceFactory(
  private val profileCache: Cache<Pair<User, Boolean?>>,
  private val postsCache: Cache<List<Post>>,
) {

  fun createLocalDataSource(): ProfileDataSource {
    return ProfileLocalDataSource(profileCache, postsCache)
  }

  fun createRemoteDataSource(): ProfileDataSource {
    return FireProfileDataSource()
  }

  fun createFromUser(uuid: String?): ProfileDataSource {
    if (uuid != null)
      return createRemoteDataSource()

    if (profileCache.isCached()) {
      return ProfileLocalDataSource(profileCache, postsCache)
    }
    return createRemoteDataSource()
  }

  fun createFromPosts(uuid: String?): ProfileDataSource {
    if (uuid != null)
      return createRemoteDataSource()

    if (postsCache.isCached()) {
      return ProfileLocalDataSource(profileCache, postsCache)
    }
    return createRemoteDataSource()
  }

}