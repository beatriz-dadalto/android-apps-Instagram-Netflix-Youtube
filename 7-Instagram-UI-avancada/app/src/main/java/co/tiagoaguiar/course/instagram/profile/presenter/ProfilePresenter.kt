package co.tiagoaguiar.course.instagram.profile.presenter

import android.net.Uri
import co.tiagoaguiar.course.instagram.R
import co.tiagoaguiar.course.instagram.common.base.RequestCallback
import co.tiagoaguiar.course.instagram.common.model.Database
import co.tiagoaguiar.course.instagram.common.model.Post
import co.tiagoaguiar.course.instagram.common.model.UserAuth
import co.tiagoaguiar.course.instagram.profile.Profile
import co.tiagoaguiar.course.instagram.profile.data.ProfileRepository
import co.tiagoaguiar.course.instagram.register.RegisterNameAndPassword
import co.tiagoaguiar.course.instagram.register.RegisterPhoto
import co.tiagoaguiar.course.instagram.register.data.RegisterCallback
import co.tiagoaguiar.course.instagram.register.data.RegisterRepository
import java.lang.RuntimeException

class ProfilePresenter(
    private var view: Profile.View?,
    private val repository: ProfileRepository
) : Profile.Presenter {

    override fun fetchUserProfile() {
        view?.showProgress(true)
        val userUUID = Database.sessionAuth?.uuid ?: throw RuntimeException("User not found")
        repository.fetchUserProfile(userUUID, object : RequestCallback<UserAuth> {
            override fun onSuccess(data: UserAuth) {
                view?.displayUserProfile(data)
            }

            override fun onFailure(message: String) {
                view?.displayRequestFailure(message)
            }

            override fun onComplete() {

            }
        })
    }

    override fun fetchUserPosts() {
        val userUUID = Database.sessionAuth?.uuid ?: throw RuntimeException("User not found")
        repository.fetchuserPosts(userUUID, object : RequestCallback<List<Post>> {
            override fun onSuccess(data: List<Post>) {
                if (data.isEmpty()) {
                    view?.displayEmptyPosts()
                } else {
                    view?.displayFullPosts(data)
                }
            }

            override fun onFailure(message: String) {
                view?.displayRequestFailure(message)
            }

            override fun onComplete() {
                view?.showProgress(false)
            }
        })
    }

    // retirar a referência
    override fun onDestroy() {
        view = null
    }

}