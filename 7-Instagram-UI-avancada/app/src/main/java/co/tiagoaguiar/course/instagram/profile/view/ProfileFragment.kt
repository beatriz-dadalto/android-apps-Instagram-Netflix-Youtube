package co.tiagoaguiar.course.instagram.profile.view

import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import co.tiagoaguiar.course.instagram.R
import co.tiagoaguiar.course.instagram.common.base.BaseFragment
import co.tiagoaguiar.course.instagram.common.base.DependencyInjector
import co.tiagoaguiar.course.instagram.common.model.Post
import co.tiagoaguiar.course.instagram.common.model.UserAuth
import co.tiagoaguiar.course.instagram.databinding.FragmentProfileBinding
import co.tiagoaguiar.course.instagram.profile.Profile
import co.tiagoaguiar.course.instagram.profile.presenter.ProfilePresenter
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProfileFragment : BaseFragment<FragmentProfileBinding, Profile.Presenter>(
    R.layout.fragment_profile,
    FragmentProfileBinding::bind
), Profile.View, BottomNavigationView.OnNavigationItemSelectedListener {

    override lateinit var presenter: Profile.Presenter

    private val adapter = PostAdapter()
    private var uuid: String? = null

    override fun setupPresenter() {
        val repository = DependencyInjector.profileRepository()
        presenter = ProfilePresenter(this, repository)
    }

    override fun setupViews() {
        uuid = arguments?.getString(KEY_USER_ID)
        binding?.profileRv?.layoutManager = GridLayoutManager(requireContext(), 3)
        binding?.profileRv?.adapter = adapter
        binding?.profileNavTabs?.setOnNavigationItemSelectedListener(this)

        presenter.fetchUserProfile(uuid)
    }

    override fun showProgress(enabled: Boolean) {
        binding?.profileProgress?.visibility = if (enabled) View.VISIBLE else View.GONE
    }

    override fun displayUserProfile(user: Pair<UserAuth, Boolean?>) {
        // destructuring
        val (userAuth, following) = user

        binding?.profileTxtPostsCount?.text = userAuth.postCount.toString()
        binding?.profileTxtFollowingCount?.text = userAuth.followingCount.toString()
        binding?.profileTxtFollowersCount?.text = userAuth.followersCount.toString()
        binding?.profileTxtUsername?.text = userAuth.name
        binding?.profileTxtBio?.text = "TODO"
        binding?.profileImgIcon?.setImageURI(userAuth.photoUri)

        binding?.profileBtnEditProfile?.text = when (following) {
            null -> getString(R.string.edit_profile)
            true -> getString(R.string.unfollow)
            false -> getString(R.string.follow)
        }

        presenter.fetchUserPosts(uuid)
    }

    override fun displayRequestFailure(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    override fun displayEmptyPosts() {
        binding?.profileTxtEmpty?.visibility = View.VISIBLE
        binding?.profileRv?.visibility = View.GONE
    }

    override fun displayFullPosts(posts: List<Post>) {
        binding?.profileTxtEmpty?.visibility = View.GONE
        binding?.profileRv?.visibility = View.VISIBLE
        adapter.items = posts
        adapter.notifyDataSetChanged()
    }

    override fun getMenu(): Int {
        return R.menu.menu_profile
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_profile_grid -> {
                binding?.profileRv?.layoutManager = GridLayoutManager(requireContext(), 3)
            }

            R.id.menu_profile_list -> {
                binding?.profileRv?.layoutManager = LinearLayoutManager(requireContext())
            }
        }
        return true
    }

    companion object {
        const val KEY_USER_ID = "key_user_id"
    }

}