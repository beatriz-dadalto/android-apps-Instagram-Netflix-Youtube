package co.tiagoaguiar.course.instagram.main.view

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.WindowInsetsController
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import co.tiagoaguiar.course.instagram.R
import co.tiagoaguiar.course.instagram.camera.view.CameraFragment
import co.tiagoaguiar.course.instagram.common.extension.replaceFragment
import co.tiagoaguiar.course.instagram.databinding.ActivityMainBinding
import co.tiagoaguiar.course.instagram.home.view.HomeFragment
import co.tiagoaguiar.course.instagram.profile.view.ProfileFragment
import co.tiagoaguiar.course.instagram.search.view.SearchFragment
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding

    private lateinit var homeFragment: Fragment
    private lateinit var searchFragment: Fragment
    private lateinit var cameraFragment: Fragment
    private lateinit var profileFragment: Fragment
    private lateinit var currentFragment: Fragment

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.setSystemBarsAppearance(
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
            )
            window.statusBarColor = ContextCompat.getColor(this, R.color.gray)
        }

        // toolbar responsavel por escutar os eventos de acao em cima tela
        setSupportActionBar(binding.mainToolbar)

        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_insta_camera)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "" // garantir que deixa os icones do lado esquerdo

        homeFragment = HomeFragment()
        searchFragment = SearchFragment()
        cameraFragment = CameraFragment()
        profileFragment = ProfileFragment()

        currentFragment = homeFragment

        /*
            adicionar/carregar todos fragments mas esconder os que não quero mostrar primeiro
            vantagem: nao vai dar replaceFragment que busca todos dados novamente
            desvantagem: vai carregar informações que talvez o user nao vai usar
            onNavigationItemSelected mostra o fragment
         */
        supportFragmentManager.beginTransaction().apply {
            add(R.id.main_fragment, profileFragment, "3").hide(profileFragment)
            add(R.id.main_fragment, cameraFragment, "2").hide(cameraFragment)
            add(R.id.main_fragment, searchFragment, "1").hide(searchFragment)
            add(R.id.main_fragment, homeFragment, "0")
            commit()
        }

        binding.mainBottomNav.setOnNavigationItemSelectedListener(this)
        binding.mainBottomNav.selectedItemId = R.id.menu_bottom_home
    }

    private fun setScrollToolbarEnabled(enabled: Boolean) {
        // pq as AppBarLayout? pq mainToolbar eh filho de AppBarLayout
        val params = binding.mainToolbar.layoutParams as AppBarLayout.LayoutParams
        // pq as CoordinatorLayout? pq mainAppbar eh filho de CoordinatorLayout
        val coordinatParams = binding.mainAppbar.layoutParams as CoordinatorLayout.LayoutParams

        if (enabled) {
            params.scrollFlags =
                AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS
            coordinatParams.behavior = AppBarLayout.Behavior()
        } else {
            params.scrollFlags = 0
            coordinatParams.behavior = null
        }
        binding.mainAppbar.layoutParams = coordinatParams
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var scrollToolbarEnabled = false
        when (item.itemId) {
            R.id.menu_bottom_home -> {
                // if para nao empilhar se clicar varias vezes no icone
                if (currentFragment == homeFragment) return false
                supportFragmentManager.beginTransaction().hide(currentFragment).show(homeFragment).commit()
                currentFragment = homeFragment
            }

            R.id.menu_bottom_search -> {
                if (currentFragment == searchFragment) return false
                supportFragmentManager.beginTransaction().hide(currentFragment).show(searchFragment).commit()
                currentFragment = searchFragment
            }

            R.id.menu_bottom_add -> {
                if (currentFragment == cameraFragment) return false
                supportFragmentManager.beginTransaction().hide(currentFragment).show(cameraFragment).commit()
                currentFragment = cameraFragment
            }

            R.id.menu_bottom_profile -> {
                if (currentFragment == profileFragment) return false
                supportFragmentManager.beginTransaction().hide(currentFragment).show(profileFragment).commit()
                currentFragment = profileFragment
                scrollToolbarEnabled = true
            }
        }

        setScrollToolbarEnabled(scrollToolbarEnabled)

        // se nao for nulo
//        currentFragment?.let { currentFragment ->
//            replaceFragment(R.id.main_fragment, currentFragment)
//        }
        return true
    }
}