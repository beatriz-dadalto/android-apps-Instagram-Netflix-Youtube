package co.tiagoaguiar.course.instagram.common.view

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import co.tiagoaguiar.course.instagram.R
import co.tiagoaguiar.course.instagram.databinding.DialogCustomBinding

class CustomDialog(context: Context) : Dialog(context) {

    private lateinit var binding: DialogCustomBinding

    private lateinit var txtButtons: Array<TextView>
    private var titleId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogCustomBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun setTitle(titleId: Int) {
        this.titleId = titleId
    }

    /*
        prefira colocar a interface no ultimo parametro pq assim pode  abrir um bloco {}.
        exemplo: nessa fun addButton vai pedir 2 param, um Int e o outro a impl da interface
        ficaria assim:
        addButton(R.string.photo, R.string.gallery) {
            Log.i("TESTE", (it as TextView).text.toString())
        }
     */
    fun addButton(vararg texts: Int, listener: View.OnClickListener) {
        txtButtons = Array(texts.size) {
            TextView(context)
        }

        texts.forEachIndexed { index, txtId ->
            txtButtons[index].id = txtId
            txtButtons[index].setText(txtId)
            txtButtons[index].setOnClickListener {
                listener.onClick(it)
                dismiss()
            }
        }
    }

    override fun show() {
        super.show()

        // se titleId nao for null coloque o conteudo de titleId no campo de texto txtTitle
        titleId?.let {
            binding.dialogTitle.setText(it)
        }

        for (textView in txtButtons) {
            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            layoutParams.setMargins(30, 50, 30, 50)
            binding.dialogContainer.addView(textView, layoutParams)
        }
    }
}