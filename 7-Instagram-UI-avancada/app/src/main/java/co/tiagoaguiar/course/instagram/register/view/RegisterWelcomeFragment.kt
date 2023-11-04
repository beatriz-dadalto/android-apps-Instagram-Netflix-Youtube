package co.tiagoaguiar.course.instagram.register.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import co.tiagoaguiar.course.instagram.R
import co.tiagoaguiar.course.instagram.databinding.FragmentRegisterWelcomeBinding
import java.lang.IllegalArgumentException

class RegisterWelcomeFragment : Fragment(R.layout.fragment_register_welcome) {

    private var binding: FragmentRegisterWelcomeBinding? = null
    private var fragmentAttachListener: FragmentAttachListener? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding  = FragmentRegisterWelcomeBinding.bind(view)

        val name = arguments?.getString(KEY_NAME) ?: throw IllegalArgumentException("Name not found")

        binding?.let {
            with(it) {
                registerTxtWelcome.text = getString(R.string.welcome_to_instagram, name)

                registerBtnNext.isEnabled = true
                registerBtnNext.setOnClickListener {
                    fragmentAttachListener?.goToPhotoScreen()
                }
            }
        }
    }

    /*
        Toda vez que um fragment eh anexado dentro de uma Activity esse método é disparado.
        pq vai usar esse onAttach? pra dizer para Activity que o fragment foi adicionado
        dentro dela e agora eu posso tratar essa Activity sendo um listener para esse fragment
    */
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentAttachListener) {
            fragmentAttachListener = context
        }
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    companion object {
        const val KEY_NAME = "key_name"
    }
}