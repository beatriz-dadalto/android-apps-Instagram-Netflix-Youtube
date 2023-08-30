package co.tiagoaguiar.netflixremake.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Looper
import android.util.Log
import java.io.IOException
import java.io.InputStream
import java.net.URL
import java.util.concurrent.Executors
import javax.net.ssl.HttpsURLConnection

/*
    Classe para realizar de forma nativa download de imagens da INTERNET.
    *uma lib usada para realizar download de imagens eh a Picasso
 */
class DownloadImageTask(private val callback: Callback) {

    private val handler = Handler(Looper.getMainLooper())
    private val executor = Executors.newSingleThreadExecutor()

    interface Callback {
        fun onResult(bitmap: Bitmap)
    }

    // nesse momento, estamos utilizando a UI-thread (1)
    fun execute(url: String) {
        executor.execute {
            var urlConnection: HttpsURLConnection? = null
            var stream: InputStream? = null
            // nesse momento, estamos utilizando a NOVA-thread [processo paralelo] (2)
            try {
                val requestURL = URL(url) // abrir uma url
                urlConnection = requestURL.openConnection() as HttpsURLConnection // abrir a conexão
                urlConnection.readTimeout = 2000
                urlConnection.connectTimeout = 2000

                val statusCode: Int = urlConnection.responseCode
                if (statusCode > 400) {
                    throw IOException(" Erro na comunicação com o servidor!")
                }

                // sequencia bytes
                stream = urlConnection.inputStream

                val bitmap = BitmapFactory.decodeStream(stream)

                handler.post {
                    // aqui roda dentro de UI-thread
                    callback.onResult(bitmap)
                }
            } catch (e: IOException) {
                val message = e.message ?: " erro desconhecido"
                Log.e("TESTE", message, e)
            } finally {
                urlConnection?.disconnect()
                stream?.close()
            }
        }
    }
}