package co.tiagoaguiar.course.instagram.register.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import co.tiagoaguiar.course.instagram.R
import co.tiagoaguiar.course.instagram.common.base.DependencyInjector
import co.tiagoaguiar.course.instagram.common.util.TxtWatcher
import co.tiagoaguiar.course.instagram.databinding.FragmentRegisterEmailBinding
import co.tiagoaguiar.course.instagram.databinding.FragmentRegisterNamePasswordBinding
import co.tiagoaguiar.course.instagram.register.RegisterEmail
import co.tiagoaguiar.course.instagram.register.presentation.RegisterEmailPresenter

class RegisterNamePasswordFragment : Fragment(R.layout.fragment_register_name_password) {

    private var binding: FragmentRegisterNamePasswordBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRegisterNamePasswordBinding.bind(view)

        val email = arguments?.getString(KEY_EMAIL)
        Log.i("TESTE", email.toString())
    }

    override fun onDestroy() {
        // sempre que uma variável passa a ficar null o
        // garbage collector "varre" e limpa livrando assim recursos
        binding = null
        super.onDestroy()
    }

    companion object {
        const val KEY_EMAIL = "key_email"
    }
}