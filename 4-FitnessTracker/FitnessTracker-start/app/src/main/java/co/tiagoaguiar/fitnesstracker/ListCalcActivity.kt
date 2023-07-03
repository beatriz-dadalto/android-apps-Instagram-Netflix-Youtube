package co.tiagoaguiar.fitnesstracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.tiagoaguiar.fitnesstracker.model.Calc
import java.text.SimpleDateFormat
import java.util.Locale

class ListCalcActivity : AppCompatActivity() {

    private lateinit var rvListCalc: RecyclerView
    private val calcItens = mutableListOf<Calc>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_calc)

        val type = intent?.extras?.getString("type") ?: throw IllegalStateException("type not found")

        //config ADAPTER
        val adapter = ListCalcAdapter(calcItens)

        // config recyclerView
        rvListCalc = findViewById(R.id.rv_listCalc)
        rvListCalc.adapter = adapter
        rvListCalc.layoutManager = LinearLayoutManager(this)

        Thread {
            val app = application as App
            val dao = app.db.calcDao()
            val response = dao.getRegisterByType(type)

            runOnUiThread {
                calcItens.addAll(response)
                adapter.notifyDataSetChanged()
            }
        }.start()


    }

    private inner class ListCalcAdapter(private val calcItens: List<Calc>) : RecyclerView.Adapter<ListCalcAdapter.ListCalcViewHolder>() {

        private inner class ListCalcViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            fun bind(item: Calc) {
                val tv = itemView as TextView

                // sempre q precisar converter de Date() p String precisa do SimpleDateFormat sdf
                val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale("PT", "BR"))

                val data = sdf.format(item.createdDate)
                val response = item.response

                tv.text = getString(R.string.list_response, response, data)
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListCalcViewHolder {
            val view = layoutInflater.inflate(android.R.layout.simple_list_item_1, parent, false)
            return ListCalcViewHolder(view)
        }

        override fun getItemCount(): Int {
            return calcItens.size
        }

        override fun onBindViewHolder(holder: ListCalcViewHolder, position: Int) {
            val itemCurrent = calcItens[position]
            holder.bind(itemCurrent)
        }
    }
}