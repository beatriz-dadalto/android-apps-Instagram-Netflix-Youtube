package co.tiagoaguiar.fitnesstracker

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvMain: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainItens = mutableListOf<MainItem>()
        mainItens.add(
            MainItem(
                id = 1,
                drawableId = R.drawable.ic_baseline_wb_sunny_24,
                textStringId = R.string.imc,
                color = Color.GREEN
            )
        )
        mainItens.add(
            MainItem(
                id = 2,
                drawableId = R.drawable.ic_baseline_volunteer_activism_24,
                textStringId = R.string.tmb,
                color = Color.MAGENTA
            )
        )

        /*
            Como mostrar uma lista de um componente ou layout?
            1) criar o layout XML
            2) onde a recyclerView vai aparecer (tela)
            3) a lógica - conectar o xml da celula DENTRO do RecyclerView + a sua qtd de elementos dinamicos
         */
        val adapter = MainAdapter(mainItens)
        rvMain = findViewById(R.id.rv_main)
        rvMain.adapter = adapter
        rvMain.layoutManager = GridLayoutManager(this,2)

        /*
            Classe Adapter para administrar a recyclerView e suas celulas (os seus layouts de itens)
         */

    }

    private inner class MainAdapter(private val mainItens: List<MainItem>) :
        RecyclerView.Adapter<MainViewHolder>() {
        // 1 - Qual eh o layout XML da célula específica (item)
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
            val view = layoutInflater.inflate(R.layout.main_item, parent, false)
            return MainViewHolder(view)
        }

        // 2 - Disparado toda vez que houver uma rolagem na tela e for necessário trocar o conteúdo
        override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
            val itemCurrent = mainItens[position]
            holder.bind(itemCurrent)
        }

        // 3 - Informar quantas células essa listagem terá
        override fun getItemCount(): Int {
            return mainItens.size
        }

    }

    // eh a classe da celula (o layout) em si
    private class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: MainItem) {
            val img: ImageView = itemView.findViewById(R.id.item_img_icon)
            val name: TextView = itemView.findViewById(R.id.item_txt_name)
            val container: LinearLayout = itemView.findViewById(R.id.item_container_imc)

            img.setImageResource(item.drawableId)
            name.setText(item.textStringId)
            container.setBackgroundColor(item.color)
        }
    }
}