package co.tiagoaguiar.netflixremake

import android.graphics.drawable.LayerDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.tiagoaguiar.netflixremake.model.Movie
import co.tiagoaguiar.netflixremake.model.MovieDetail
import co.tiagoaguiar.netflixremake.util.MovieTask
import java.lang.IllegalStateException

class MovieActivity : AppCompatActivity(), MovieTask.Callback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        val txtTitle: TextView = findViewById(R.id.movie_txt_title)
        val txtDesc: TextView = findViewById(R.id.movie_txt_desc)
        val txtCast: TextView = findViewById(R.id.movie_txt_cast)
        val rv: RecyclerView = findViewById(R.id.movie_rv_similar)

        val id =
            intent?.getIntExtra("id", 0) ?: throw IllegalStateException("ID não foi encontrado!")

        val url =
            "https://api.tiagoaguiar.co/netflixapp/movie/$id?apiKey=1d8f507d-9c2d-4678-a16e-ad416dd113b9"

        // nesse momento vai chamar o url na outra Thread
        MovieTask(this).execute(url)

        txtTitle.text = "Batman Begins"
        txtDesc.text = "Essa é a descrição do filme do Batman"
        txtCast.text = getString(R.string.cast, "Ator A, Ator B, Atriz A, Atriz B")

        val movies = mutableListOf<Movie>()

        rv.layoutManager = GridLayoutManager(this, 3)
        rv.adapter = MovieAdapter(movies, R.layout.movie_item_similar)

        val toolbar: Toolbar = findViewById(R.id.movie_toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = null

        // busquei o desenhavel (layer-list)
        val layerDrawable: LayerDrawable =
            ContextCompat.getDrawable(this, R.drawable.shadows) as LayerDrawable
        // busquei o filme que eu quero
        val movieCover = ContextCompat.getDrawable(this, R.drawable.movie_4)
        // atribui a esse layer-list o novo filme
        layerDrawable.setDrawableByLayerId(R.id.cover_drawable, movieCover)
        // set no imageView
        val coverImg: ImageView = findViewById(R.id.movie_img)
        coverImg.setImageDrawable(layerDrawable)
    }

    // disparar um evento sempre que um item da toolbar eh clicado
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onPreExecute() {

    }

    override fun onFailure(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun onResult(movieDetail: MovieDetail) {
        Log.i("Teste", movieDetail.toString())
    }

}