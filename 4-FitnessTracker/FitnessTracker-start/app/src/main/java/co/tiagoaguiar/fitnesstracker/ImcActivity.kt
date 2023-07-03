package co.tiagoaguiar.fitnesstracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import co.tiagoaguiar.fitnesstracker.model.Calc

class ImcActivity : AppCompatActivity() {

    private lateinit var editWeight: EditText
    private lateinit var editHeight: EditText
    private lateinit var btnSend: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc)

        editWeight = findViewById(R.id.edit_imc_weight)
        editHeight = findViewById(R.id.edit_imc_height)
        btnSend = findViewById(R.id.btn_imc_send)

        btnSend.setOnClickListener {
            if (!validate()) {
                Toast.makeText(this, R.string.fields_message, Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            val weight = editWeight.text.toString().toInt()
            val height = editHeight.text.toString().toInt()
            val result = calculateImc(weight, height)
            Log.d("TESTE", "resultado: $result")

            val imcResponseId = imcResponse(result)

            AlertDialog.Builder(this)
                .setTitle(getString(R.string.imc_response, result))
                .setMessage(imcResponseId)
                .setPositiveButton(
                    android.R.string.ok
                ) { dialog, which -> TODO("Not yet implemented") }
                .setNegativeButton(R.string.save) { dialog, which ->
                    Thread {
                        val app = application as App
                        val dao = app.db.calcDao()
                        dao.insert(Calc(type = "imc", response = result))

                        runOnUiThread {
                            Toast.makeText(
                                this@ImcActivity,
                                R.string.calc_saved,
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }.start()
                }
                .create()
                .show()

            hideKeyboard()
        }
    }

    private fun hideKeyboard() {
        val service = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        service.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }

    @StringRes
    private fun imcResponse(imc: Double): Int {
        return when {
            imc < 15.0 -> R.string.imc_severely_low_weight
            imc < 16.0 -> R.string.imc_very_low_weight
            imc < 18.5 -> R.string.imc_low_weight
            imc < 25.0 -> R.string.normal
            imc < 30.0 -> R.string.imc_high_weight
            imc < 35.0 -> R.string.imc_so_high_weight
            imc < 40.0 -> R.string.imc_severely_high_weight
            else -> R.string.imc_extreme_weight
        }
    }

    private fun calculateImc(weight: Int, height: Int): Double {
        return weight / ((height / 100.0) * (height / 100.0))
    }

    private fun validate(): Boolean {
        return (editWeight.text.toString().isNotEmpty()
                && editHeight.text.toString().isNotEmpty()
                && !editHeight.text.toString().startsWith("0")
                && !editWeight.text.toString().startsWith("0"))
    }
}

