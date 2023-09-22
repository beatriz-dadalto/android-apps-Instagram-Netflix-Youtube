package co.tiagoaguiar.tutorial.jokerappdev.presentation

import android.graphics.Color
import android.os.Handler
import android.os.Looper
import co.tiagoaguiar.tutorial.jokerappdev.data.CategoryRemoteDataSource
import co.tiagoaguiar.tutorial.jokerappdev.data.ListCategoryCallback
import co.tiagoaguiar.tutorial.jokerappdev.model.Category
import co.tiagoaguiar.tutorial.jokerappdev.view.HomeFragment

class HomePresenter(
    private val view: HomeFragment,
    private val dataSource: CategoryRemoteDataSource = CategoryRemoteDataSource()
) : ListCategoryCallback {

    // INPUT
    fun findAllCategories() {
        view.showProgress()
        dataSource.findAllCategories(this)
    }

    // output (SUCESSO | FALHA | COMPLETE)
    override fun onSuccess(response: List<String>) {
        // jeito 1
//        val categories = mutableListOf<CategoryItem>()
//        for (category in response) {
//            categories.add(CategoryItem(category))
//        }

        val start = 40 // Hue - matiz
        val end = 190 // Hue - matiz
        val diff = (end - start) / response.size

        // jeito 2
        val categories = response.mapIndexed { index, it ->
            val hsv = floatArrayOf(
                start + (diff * index).toFloat(),
                100.0f,
                100.0f,
            )
            Category(it, Color.HSVToColor(hsv).toLong())
        }

        view.showCategories(categories)
    }

    override fun onError(response: String) {
        view.showFailure(response)
    }

    override fun onComplete() {
        view.hideProgress()
    }


}



















