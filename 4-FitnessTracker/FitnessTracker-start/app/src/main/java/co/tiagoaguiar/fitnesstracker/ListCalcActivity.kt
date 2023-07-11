package co.tiagoaguiar.fitnesstracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.tiagoaguiar.fitnesstracker.model.Calc
import java.text.SimpleDateFormat
import java.util.Locale

class ListCalcActivity : AppCompatActivity(), OnListClickListener {

    private lateinit var rvListCalc: RecyclerView
    // adapter e result precisa estar no escopo para podermos usá-lo na hora de excluir o item
    private lateinit var adapter: ListCalcAdapter
    private lateinit var result: MutableList<Calc>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_calc)

        val type =
            intent?.extras?.getString("type") ?: throw IllegalStateException("type not found")

        result = mutableListOf<Calc>()

        //config ADAPTER
        val adapter = ListCalcAdapter(result, this)

        // config recyclerView
        rvListCalc = findViewById(R.id.rv_listCalc)
        rvListCalc.adapter = adapter
        rvListCalc.layoutManager = LinearLayoutManager(this)

        Thread {
            val app = application as App
            val dao = app.db.calcDao()
            val response = dao.getRegisterByType(type)

            runOnUiThread {
                result.addAll(response)
                adapter.notifyDataSetChanged()
            }
        }.start()
    }

    private inner class ListCalcAdapter(
        private val calcItens: List<Calc>,
        private val onListClickListener: OnListClickListener
    ) : RecyclerView.Adapter<ListCalcAdapter.ListCalcViewHolder>() {

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

        private inner class ListCalcViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            fun bind(item: Calc) {
                val tv = itemView as TextView

                // sempre q precisar converter de Date() p String precisa do SimpleDateFormat sdf
                val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale("PT", "BR"))

                val data = sdf.format(item.createdDate)
                val response = item.response

                tv.text = getString(R.string.list_response, response, data)

                tv.setOnClickListener {
                    onListClickListener.onClick(item.id, item.type)
                }

                tv.setOnLongClickListener {
                    onListClickListener.onLongClick(adapterPosition, item)
                    true
                }
            }
        }
    }

    override fun onClick(id: Int, type: String) {
        when (type) {
            "imc" -> {
                val intent = Intent(this, ImcActivity::class.java)
                intent.putExtra("updateId", id)
                startActivity(intent)
            }

            "tmb" -> {
                val intent = Intent(this, TmbActivity::class.java)
                intent.putExtra("updateId", id)
                startActivity(intent)
            }
        }
        finish()
    }

    override fun onLongClick(position: Int, calc: Calc) {
        AlertDialog.Builder(this)
            .setMessage(getString(R.string.delete_message))
            .setNegativeButton(android.R.string.cancel) { dialog, which -> }
            .setPositiveButton(android.R.string.ok) { dialog, which ->
                Thread {
                    val app = application as App
                    val dao = app.db.calcDao()

                    val response = dao.delete(calc)

                    if (response > 0) {
                        runOnUiThread {
                            result.removeAt(position)
                            adapter.notifyItemRemoved(position)
                        }
                    }
                }.start()
            }.create().show()
    }
}