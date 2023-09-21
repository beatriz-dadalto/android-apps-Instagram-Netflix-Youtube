package co.tiagoaguiar.tutorial.jokerappdev.data

import android.os.Handler
import android.os.Looper

class CategoryRemoteDataSource {

    // SIMULAR UM REQUISCAO HTTP
    // callback será o HomePresenter
    fun findAllCategories(callback: ListCategoryCallback) {
        Handler(Looper.getMainLooper()).postDelayed({
            val response = arrayListOf(
                "Categoria 1",
                "Categoria 2",
                "Categoria 3",
                "Categoria 4"
            )

            // aqui a lista ja esta pronta (response)

            // DEVOLVER OU FALHA OU SUCESSO
            callback.onSuccess(response)
            // onError("FALHA NA CONEXÃO. TENTE NOVAMENTE MAIS TARDE!")

            callback.onComplete()
        }, 4000)
    }
}