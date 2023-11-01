package co.tiagoaguiar.course.instagram.register.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import co.tiagoaguiar.course.instagram.R
import co.tiagoaguiar.course.instagram.databinding.ActivityRegisterBinding
import co.tiagoaguiar.course.instagram.register.view.RegisterNamePasswordFragment.Companion.KEY_EMAIL

class RegisterActivity : AppCompatActivity(), FragmentAttachListener {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragment = RegisterEmailFragment()

        replaceFragment(fragment)
    }

    private fun replaceFragment(fragment: Fragment) {
        if (supportFragmentManager.findFragmentById(R.id.register_fragment) == null) {
            supportFragmentManager.beginTransaction().apply {
                add(R.id.register_fragment, fragment)
                commit()
            }
        } else {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.register_fragment, fragment)
                // empilhamento. quando clicar pra voltar vai pra tela anterior
                addToBackStack(null)
                commit()
            }
        }
    }

    override fun goToNameAndPasswordScreen(email: String) {
        val args = Bundle()
        args.putString(KEY_EMAIL, email)

        val fragment = RegisterNamePasswordFragment()
        fragment.arguments = args
        replaceFragment(fragment)

        /*  FORMA ENXUTA
            val fragment = RegisterNamePasswordFragment().apply {
                arguments = Bundle().apply {
                    putString(KEY_EMAIL, email)
                }
            }
         */
    }
}