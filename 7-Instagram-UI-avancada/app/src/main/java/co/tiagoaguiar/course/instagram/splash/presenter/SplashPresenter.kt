package co.tiagoaguiar.course.instagram.splash.presenter

import co.tiagoaguiar.course.instagram.splash.Splash
import co.tiagoaguiar.course.instagram.splash.data.SplashCallback
import co.tiagoaguiar.course.instagram.splash.data.SplashRepository

class SplashPresenter(private var view: Splash.View?, private val repository: SplashRepository) :
    Splash.Presenter {
    override fun authenticated() {
        repository.session(object : SplashCallback {
            override fun onSuccess() {
                view?.goToMainScreen()
            }

            override fun onFailure() {
                view?.goToLoginScreen()
            }
        })
    }

    override fun onDestroy() {
        view = null
    }
}