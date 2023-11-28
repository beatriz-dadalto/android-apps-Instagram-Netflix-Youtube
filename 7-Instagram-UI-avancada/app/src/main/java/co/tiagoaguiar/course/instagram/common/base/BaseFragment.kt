package co.tiagoaguiar.course.instagram.common.base

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.annotation.LayoutRes
import androidx.annotation.MenuRes
import androidx.fragment.app.Fragment

/*
    abstract pq nao vou instanciar e vou criar metodos abstract para
    as classes filhas herdar e devolver instrucoes
 */
abstract class BaseFragment<T, P : BasePresenter>(
    @LayoutRes layoutId: Int,
    val bind: (View) -> T
) : Fragment(layoutId) {

    protected var binding: T? = null

    abstract var presenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (getMenu() != null) {
            // informar que esse fragmento é responsavel por gerenciar opcoes de menu
            setHasOptionsMenu(true)
        }
        setupPresenter()
    }

    override fun onDestroy() {
        binding = null
        presenter.onDestroy()
        super.onDestroy()
    }

    // ativar e inflar o menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        getMenu()?.let {
            inflater.inflate(it, menu)
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = bind(view)

        savedInstanceState?.getString("name")

        if (savedInstanceState == null) {
            setupViews()
        }

    }

    abstract fun setupViews()

    abstract fun setupPresenter()

    // layouts que nao precisam de menu retornara nada
    @MenuRes // passe apenas arquivos de menu
    open fun getMenu(): Int? {
        return null
    }
}