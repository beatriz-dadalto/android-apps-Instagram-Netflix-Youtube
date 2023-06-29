package co.tiagoaguiar.fitnesstracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvMain: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*
            Como mostrar uma lista de um componente ou layout?
            1) criar o layout XML
            2) onde a recyclerView vai aparecer (tela)
            3) a lógica - conectar o xml da celula DENTRO do RecyclerView + a sua qtd de elementos dinamicos
         */
        val adapter = MainAdapter()
        rvMain = findViewById(R.id.rv_main)
        rvMain.adapter = adapter
        rvMain.layoutManager = LinearLayoutManager(this)

        /*
            Classe Adapter para administrar a recyclerView e suas celulas (os seus layouts de itens)
         */

    }

    private inner class MainAdapter : RecyclerView.Adapter<MainViewHolder>() {
        // 1 - Qual eh o layout XML da célula específica (item)
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
            val view = layoutInflater.inflate(R.layout.main_item, parent, false)
            return MainViewHolder(view)
        }

        // 2 - Disparado toda vez que houver uma rolagem na tela e for necessário trocar o conteúdo
        override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
            TODO("Not yet implemented")
        }

        // 3 - Informar quantas células essa listagem terá
        override fun getItemCount(): Int {
            return 15
        }

    }

    // eh a classe da celula (o layout) em si
    private class MainViewHolder(view: View) : RecyclerView.ViewHolder(view)
}