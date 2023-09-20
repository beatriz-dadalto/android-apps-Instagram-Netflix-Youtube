package co.tiagoaguiar.tutorial.jokerappdev.presentation

import android.os.Handler
import android.os.Looper
import co.tiagoaguiar.tutorial.jokerappdev.model.Category
import co.tiagoaguiar.tutorial.jokerappdev.view.HomeFragment

class HomePresenter(private val view: HomeFragment) {

    // INPUT
    fun findAllCategories() {
        view.showProgress()
        fakeRequest()
    }

    // output (SUCESSO | FALHA | COMPLETE)
    fun onSuccess(response: ArrayList<String>) {
        // jeito 1
//        val categories = mutableListOf<CategoryItem>()
//        for (category in response) {
//            categories.add(CategoryItem(category))
//        }

        // jeito 2
        val categories = response.map { Category(it, 0xFFFF0000) }

        view.showCategories(categories)
    }

    fun onError(message: String) {
        view.showFailure(message)
    }

    fun onComplete() {
        view.hideProgress()
    }

    // SIMULAR UM REQUISCAO HTTP
    private fun fakeRequest() {
        Handler(Looper.getMainLooper()).postDelayed({
            val response = arrayListOf(
                "Categoria 1",
                "Categoria 2",
                "Categoria 3",
                "Categoria 4"
            )

            // aqui a lista ja esta pronta (response)

            // DEVOLVER OU FALHA OU SUCESSO
            onSuccess(response)
            // onError("FALHA NA CONEXÃO. TENTE NOVAMENTE MAIS TARDE!")

            onComplete()
        }, 4000)
    }

}


// 1. CICLO DE VIDA DO FRAGMENT FAZ A AÇÃO (CHAMAR O PRESENTER PEDINDO PARA BUSCAR AS CATEGORIAS)
// 2. O PRESENTER PEDE A LISTA DE CAT. NO MODEL
// 3. O MODEL DEVOLVE UMA LISTA List<String>
// 4. o PRESENTER FORMATAR ESSA LISTA (String) EM (Category (MODEL))
// 5. VIEW PEGA A LISTA DE List<Category> E CONVERTE PARA O List<CategoryItem>



















