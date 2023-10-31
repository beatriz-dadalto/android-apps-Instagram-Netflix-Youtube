package co.tiagoaguiar.course.instagram.register.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.tiagoaguiar.course.instagram.R
import co.tiagoaguiar.course.instagram.databinding.FragmentRegisterEmailBinding
import co.tiagoaguiar.course.instagram.register.RegisterEmail

class RegisterEmailFragment : Fragment(R.layout.fragment_register_email), RegisterEmail.View {

    private var binding: FragmentRegisterEmailBinding? = null
    override lateinit var presenter: RegisterEmail.Presenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRegisterEmailBinding.bind(view)
    }

    override fun displayEmailFailure(emailError: Int?) {
        TODO("Not yet implemented")
    }

    override fun onDestroy() {
        binding = null
        presenter.onDestroy()
        super.onDestroy()
    }

}