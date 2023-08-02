package co.tiagoaguiar.netflixremake

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.tiagoaguiar.netflixremake.model.Category
import co.tiagoaguiar.netflixremake.model.Movie

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*
        List vertical
            category A
                List horizontal -> movie1, movie2, movie3 ...
            category B
                List horizontal -> movie1, movie2, movie3 ...
         */

        val categories = mutableListOf<Category>()

        for (j in 0 until 5) {

            val movies = mutableListOf<Movie>()
            for (i in 0 until 5) {
                val movie = Movie(R.drawable.movie)
                movies.add(movie)
            }

            val category = Category(name = "cat $j", movies)
            categories.add(category)
        }

        val movies = mutableListOf<Movie>()
        for (i in 0 until 5) {
            val movie = Movie(R.drawable.movie)
            movies.add(movie)
        }

        /*
            na vertical a lista (CategoryAdapter) de categorias e dentro de cada item [TextView+
            RecyclerView horizontal] cada categoria teremos uma lista (MovieAdapter) de
            filmes [ImageView]
         */
        val rv: RecyclerView = findViewById(R.id.rv_main)
        rv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rv.adapter = CategoryAdapter(categories)
    }

}