package co.tiagoaguiar.course.instagram.common.base

import android.content.Context
import co.tiagoaguiar.course.instagram.add.data.AddFakeRemoteDataSource
import co.tiagoaguiar.course.instagram.add.data.AddLocalDataSource
import co.tiagoaguiar.course.instagram.add.data.AddRepository
import co.tiagoaguiar.course.instagram.add.data.FirebaseAddDataSource
import co.tiagoaguiar.course.instagram.home.data.FeedMemoryCache
import co.tiagoaguiar.course.instagram.home.data.HomeDataSourceFactory
import co.tiagoaguiar.course.instagram.home.data.HomeRepository
import co.tiagoaguiar.course.instagram.login.data.FirebaseLoginDataSource
import co.tiagoaguiar.course.instagram.login.data.LoginRepository
import co.tiagoaguiar.course.instagram.post.data.PostLocalDataSource
import co.tiagoaguiar.course.instagram.post.data.PostRepository
import co.tiagoaguiar.course.instagram.profile.data.PostListMemoryCache
import co.tiagoaguiar.course.instagram.profile.data.ProfileDataSourceFactory
import co.tiagoaguiar.course.instagram.profile.data.ProfileMemoryCache
import co.tiagoaguiar.course.instagram.profile.data.ProfileRepository
import co.tiagoaguiar.course.instagram.register.data.FirebaseRegisterDataSource
import co.tiagoaguiar.course.instagram.register.data.RegisterRepository
import co.tiagoaguiar.course.instagram.search.data.FirebaseSearchDataSource
import co.tiagoaguiar.course.instagram.search.data.SearchFakeRemoteDataSource
import co.tiagoaguiar.course.instagram.search.data.SearchRepository
import co.tiagoaguiar.course.instagram.splash.data.FirebaseSplashDataSource
import co.tiagoaguiar.course.instagram.splash.data.SplashRepository

object DependencyInjector {

  fun splashRepository(): SplashRepository {
    return SplashRepository(FirebaseSplashDataSource())
  }

  fun loginRepository(): LoginRepository {
    return LoginRepository(FirebaseLoginDataSource())
  }

  fun registerEmailRepository(): RegisterRepository {
    return RegisterRepository(FirebaseRegisterDataSource())
  }

  fun searchRepository(): SearchRepository {
    return SearchRepository(FirebaseSearchDataSource())
  }

  fun profileRepository(): ProfileRepository {
    return ProfileRepository(ProfileDataSourceFactory(ProfileMemoryCache, PostListMemoryCache))
  }

  fun homeRepository(): HomeRepository {
    return HomeRepository(HomeDataSourceFactory(FeedMemoryCache))
  }

  fun addRepository(): AddRepository {
    return AddRepository(FirebaseAddDataSource(), AddLocalDataSource())
  }

  fun postRepository(context: Context): PostRepository {
    return PostRepository(PostLocalDataSource(context))
  }

}