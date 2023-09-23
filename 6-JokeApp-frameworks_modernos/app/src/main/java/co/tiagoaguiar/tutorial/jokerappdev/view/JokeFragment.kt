package co.tiagoaguiar.tutorial.jokerappdev.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import co.tiagoaguiar.tutorial.jokerappdev.R

class JokeFragment : Fragment() {

    companion object {
        const val CATEGORY_KEY = "category"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // pegando dados da tela HomeFragment
        val categoryName = arguments?.getString(CATEGORY_KEY)

        // pq activity? pq a toolbar esta na activity e aqui é um Fragment inserido na activity
        activity?.findViewById<Toolbar>(R.id.toolbar)?.title = categoryName
    }
}