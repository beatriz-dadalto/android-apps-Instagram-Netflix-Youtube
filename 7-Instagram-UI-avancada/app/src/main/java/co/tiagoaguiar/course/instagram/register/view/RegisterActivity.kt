package co.tiagoaguiar.course.instagram.register.view

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import co.tiagoaguiar.course.instagram.R
import co.tiagoaguiar.course.instagram.common.view.CropperImageFragment
import co.tiagoaguiar.course.instagram.databinding.ActivityRegisterBinding
import co.tiagoaguiar.course.instagram.main.view.MainActivity
import co.tiagoaguiar.course.instagram.register.view.RegisterNamePasswordFragment.Companion.KEY_EMAIL
import co.tiagoaguiar.course.instagram.register.view.RegisterWelcomeFragment.Companion.KEY_NAME
import java.net.URI

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

    override fun goToWelcomeScreen(name: String) {
        val fragment = RegisterWelcomeFragment().apply {
            arguments = Bundle().apply {
                putString(KEY_NAME, name)
            }
        }
        replaceFragment(fragment)
    }

    override fun goToPhotoScreen() {
        val fragment = RegisterPhotoFragment()
        replaceFragment(fragment)
    }

    override fun goToMainScreen() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    /*
        Activity registre um callback que retorna um conteudo para toda vez q um user escolher esse
        conteudo eu consiga buscar o caminho desse conteudo atraves de uma URI
     */
    private val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        // mostra o caminho da imagem na galeria: content://com.android.providers.media.documents/document/image%3A33
        Log.i("TESTE", uri.toString())
        val fragment = CropperImageFragment().apply {
            arguments = Bundle().apply {
                putParcelable(CropperImageFragment.KEY_URI, uri)
            }
        }
        replaceFragment(fragment)
    }

    override fun goToGalleryScreen() {
        // que tipo de conteudo eu quero? poderia ser video, texto, imagens...
        // * = imagem de qualquer formato.
        getContent.launch("image/*")
    }
}