package co.tiagoaguiar.course.instagram.profile.data

import co.tiagoaguiar.course.instagram.common.base.RequestCallback
import co.tiagoaguiar.course.instagram.common.model.Post
import co.tiagoaguiar.course.instagram.common.model.User
import co.tiagoaguiar.course.instagram.common.model.UserAuth
import java.lang.UnsupportedOperationException

interface ProfileDataSource {

  fun fetchUserProfile(userUUID: String, callback: RequestCallback<Pair<User, Boolean?>>)

  fun fetchUserPosts(userUUID: String, callback: RequestCallback<List<Post>>)

  fun followUser(userUUID: String, isFollow: Boolean, callback: RequestCallback<Boolean>) { throw UnsupportedOperationException() }

  fun fetchSession() : String { throw UnsupportedOperationException() }

  fun putUser(response: Pair<User, Boolean?>?) { throw UnsupportedOperationException() }

  fun putPosts(response: List<Post>?) { throw UnsupportedOperationException() }

}