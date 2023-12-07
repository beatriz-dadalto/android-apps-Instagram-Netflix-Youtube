package co.tiagoaguiar.course.instagram.post.presenter

import android.net.Uri
import co.tiagoaguiar.course.instagram.post.Post
import co.tiagoaguiar.course.instagram.post.data.PostRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class PostPresenter(
    private var view: Post.View?,
    private val repository: PostRepository
) : Post.Presenter, CoroutineScope {

    private var uri: Uri? = null

    // executar paralelamente por causa da coroutine suspend
    private val job = Job()
    override val coroutineContext: CoroutineContext = job + Dispatchers.IO

    override fun fetchPictures() {
        // Aqui acontece a chamada na THREAD MAIN (UI)
        view?.showProgress(true)

        launch {
            // Aqui acontece a chamada paralela (coroutine IO)
            val pictures = repository.fetchPictures()

            withContext(Dispatchers.Main) {
                // Aqui executa de volta na MAIN THREAD
                if (pictures.isEmpty()) {
                    view?.displayEmptyPictures()
                } else {
                    view?.displayFullPictures(pictures)
                }
                view?.showProgress(false)
            }
        }
    }

    override fun selectUri(uri: Uri) {
        this.uri = uri
    }

    override fun getSelectedUri(): Uri? {
        return uri
    }

    override fun onDestroy() {
        job.cancel()
        view = null
    }
}