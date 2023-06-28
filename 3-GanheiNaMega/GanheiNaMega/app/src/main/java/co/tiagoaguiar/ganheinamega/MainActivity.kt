package co.tiagoaguiar.ganheinamega

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.util.Random

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

            val text = editText.text.toString()

            numberGenerator(text, txtResult)
        }
    }

    private fun numberGenerator(text: String, txtResult: TextView) {

        // VERIFICAR ERROS PRIMEIRO

        // erro 1
        if (text.isEmpty()) {
            Toast.makeText(this, "Informe um numero entre 6 e 15", Toast.LENGTH_LONG).show()
            return
        }

        // erro 2
        val quantity = text.toInt()
        if (quantity < 6 || quantity > 15) {
            Toast.makeText(this, "Informe um numero entre 6 e 15", Toast.LENGTH_LONG).show()
            return
        }

        // AQUI EXECUTA SE NAO TIVER ERRO

        val random = Random()
        val numbers = mutableSetOf<Int>()

        while (true) {
            val number = random.nextInt(60)
            numbers.add(number + 1)
            if (numbers.size == quantity) {
                break
            }
        }

        txtResult.text = numbers.joinToString(" - ")
    }

}

// 1- via XML
//    fun buttonClicked(view: View) {
//        Log.i("TESTE", "Botão Clicado")
//    }

// 2- variavel que seja do tipo View.OnClickListener (interface)
// quem chama o onClick é o proprio SDK do android que dispara apos o evento de touch
//    val buttonClickListner = View.OnClickListener { Log.i("TESTE", "Botão Clicado") }

