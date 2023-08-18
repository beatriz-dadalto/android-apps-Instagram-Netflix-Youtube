package co.tiagoaguiar.netflixremake.util

import android.util.Log
import java.io.IOException
import java.net.URL
import java.util.concurrent.Executors
import javax.net.ssl.HttpsURLConnection

class CategoryTask {

    // fazer uma chamada paralela
    fun execute(url: String) {
        // nesse momento estamos utilizando a UI-Thread (1)
        val executor = Executors.newSingleThreadExecutor()

        executor.execute {
            try {
                // nesse momento estamos utilizando a NOVA THREAD [processo paralelo (2)]
                val requestURL = URL(url) // abrir uma URL
                val urlConnection = requestURL.openConnection() as HttpsURLConnection // abrir a conexão
                urlConnection.readTimeout = 2000 // tempo de leitura (2 segundos)
                urlConnection.connectTimeout = 2000 // tempo para conexão (2 segundos)

                val statusCode = urlConnection.responseCode

                if (statusCode > 400) {
                    throw IOException("Erro na comunicação com o servidor!")
                }

                // forma 1: simples e rápida
                val stream = urlConnection.inputStream // HTTP devolve uma sequência de bytes
                val jsonAsString = stream.bufferedReader().use { it.readText() } // bytes para String
                Log.i("TESTE", jsonAsString)

                // forma 2:
            } catch (e: IOException) {
                Log.e("TESTE", e.message ?: " Erro desconhecido", e)
            }


        }
    }
}