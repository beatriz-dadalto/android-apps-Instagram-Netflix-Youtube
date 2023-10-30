package co.tiagoaguiar.course.instagram.login.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import co.tiagoaguiar.course.instagram.common.util.TxtWatcher
import co.tiagoaguiar.course.instagram.databinding.ActivityLoginBinding
import co.tiagoaguiar.course.instagram.login.Login

class LoginActivity : AppCompatActivity(), Login.View {

    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // forma enxuta. desse jeito não preciso colocar binding em todos elementos xml da tela
        with(binding) {
            loginEditEmail.addTextChangedListener(watcher)
            loginEditPassword.addTextChangedListener(watcher)

            loginBtnEnter.setOnClickListener {
                TODO("Call the PRESENTER layer")
            }
        }
    }

    private val watcher = TxtWatcher {
        binding.loginBtnEnter.isEnabled = it.isNotEmpty()
    }

    override fun showProgress(enabled: Boolean) {
        binding.loginBtnEnter.showProgress(true)
    }

    override fun displayEmailFailure(emailError: Int?) {
        binding.loginEditEmailInput.error = emailError?.let { getString(it) }
    }

    override fun displayPasswordFailure(passwordError: Int?) {
        binding.loginEditPasswordInput.error = passwordError?.let { getString(it) }
    }

    override fun onUserAuthenticated() {
        TODO("Go to Home Screen")
    }

    override fun onUserUnauthorized() {
        TODO("Show alert")
    }

}