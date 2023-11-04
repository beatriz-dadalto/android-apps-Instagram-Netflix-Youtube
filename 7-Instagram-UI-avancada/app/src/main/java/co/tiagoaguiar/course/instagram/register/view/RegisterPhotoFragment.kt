package co.tiagoaguiar.course.instagram.register.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import co.tiagoaguiar.course.instagram.R
import co.tiagoaguiar.course.instagram.common.view.CustomDialog
import co.tiagoaguiar.course.instagram.databinding.FragmentRegisterPhotoBinding

class RegisterPhotoFragment : Fragment(R.layout.fragment_register_photo) {

    /*
        pq nao lateinit?
            pq vai usar o ciclo de vida do fragment do onCreate e onDestroy para
            livrar espaço de RAM para quando trocar de fragment.
        -> lateinit var ja declara como nao nullable, ou seja, nao aceita null
     */
    private var binding: FragmentRegisterPhotoBinding? = null
    private var fragmentAttachListener: FragmentAttachListener? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRegisterPhotoBinding.bind(view)

        binding?.let {
            with(it) {
                registerBtnJump.setOnClickListener {
                    fragmentAttachListener?.goToMainScreen()
                }

                registerBtnNext.isEnabled = true
                registerBtnNext.setOnClickListener {
                    val customDialog = CustomDialog(requireContext())
                    customDialog.addButton(R.string.photo, R.string.gallery) { dialogOption ->
                        when (dialogOption.id) {
                            R.string.photo -> {
                                Log.i("TESTE", "foto uhull")
                            }

                            R.string.gallery -> {
                                Log.i("TESTE", "galeria ebaaa")
                            }
                        }
                    }
                    customDialog.show()
                }
            }
        }
    }

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
}