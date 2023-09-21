package co.tiagoaguiar.tutorial.jokerappdev.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// object eh como se fosse singleton. enquanto estiver rodando fica disponivel para usar.
object HTTPClient {

    private const val BASE_URL = "https://api.tiagoaguiar.co/jokerapp/"
    const val API_KEY = "1d8f507d-9c2d-4678-a16e-ad416dd113b9"

    private fun httpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    fun retrofit() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient())
        .build()
}