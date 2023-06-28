package co.tiagoaguiar.ganheinamega

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // aqui onde decide o que o app vai fazer
        setContentView(R.layout.activity_main)

        // buscar os objetos e ter a referencia deles
        val editText: EditText = findViewById(R.id.edit_number)
        val txtResult: TextView = findViewById(R.id.txt_result)
        val btnGenerate: Button = findViewById(R.id.btn_generate)

        // 3 opcoes de fazer click no btn
        // 1- via XML (lá no XML onClick)
        // 2- variavel que seja do tipo View.OnClickListener (interface)
//        btnGenerate.setOnClickListener(buttonClickListner)

        // 3- mais simples - bloco de codigo que sera disparado pelo onCLickListener
        btnGenerate.setOnClickListener {
            Log.i("TESTE", "Botão Clicado")
        }
    }

    // 1- via XML
//    fun buttonClicked(view: View) {
//        Log.i("TESTE", "Botão Clicado")
//    }

    // 2- variavel que seja do tipo View.OnClickListener (interface)
    // quem chama o onClick é o proprio SDK do android que dispara apos o evento de touch
//    val buttonClickListner = View.OnClickListener { Log.i("TESTE", "Botão Clicado") }

}