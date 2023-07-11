package co.tiagoaguiar.fitnesstracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import co.tiagoaguiar.fitnesstracker.model.Calc

class TmbActivity : AppCompatActivity() {

    private lateinit var lifestyle: AutoCompleteTextView
    private lateinit var editWeight: EditText
    private lateinit var editHeight: EditText
    private lateinit var editAge: EditText
    private lateinit var btnSend: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tmb)

        lifestyle = findViewById(R.id.auto_lifestyle)
        editWeight = findViewById(R.id.edit_tmb_weight)
        editHeight = findViewById(R.id.edit_tmb_height)
        editAge = findViewById(R.id.edit_tmb_age)
        btnSend = findViewById(R.id.btn_tmb_send)

        btnSend.setOnClickListener {
            if (!validate()) {
                Toast.makeText(this, R.string.fields_message, Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            val weight = editWeight.text.toString().toInt()
            val height = editHeight.text.toString().toInt()
            val age = editAge.text.toString().toInt()

            val result = calculateTmb(weight, height, age)
            val response = tmbRequest(result)

            AlertDialog.Builder(this)
                .setMessage(getString(R.string.tmb_response, response))
                .setPositiveButton(
                    android.R.string.ok
                ) { dialog, which -> TODO("Not yet implemented") }
                .setNegativeButton(R.string.save) { dialog, which ->
                    Thread {
                        val app = application as App
                        val dao = app.db.calcDao()
                        dao.insert(Calc(type = "tmb", response = response))

                        runOnUiThread {
                            openListActivity()
                        }
                    }.start()
                }
                .create()
                .show()

            hideKeyboard()
        }

        val items = resources.getStringArray(R.array.tmb_lifestyle)
        lifestyle.setText(items.first())
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
        lifestyle.setAdapter(adapter)

    }

    private fun tmbRequest(tmb: Double): Double {
        val items = resources.getStringArray(R.array.tmb_lifestyle)
        return when {
            lifestyle.text.toString() == items[0] -> tmb * 1.2
            lifestyle.text.toString() == items[1] -> tmb * 1.375
            lifestyle.text.toString() == items[2] -> tmb * 1.55
            lifestyle.text.toString() == items[3] -> tmb * 1.775
            lifestyle.text.toString() == items[4] -> tmb * 1.9
            else -> 0.0
        }
    }

    private fun calculateTmb(weight: Int, height: Int, age: Int): Double {
        return 66 + (13.8 * weight) + (5 * height) - (6.8 * age)
    }

    private fun validate(): Boolean {
        return (editWeight.text.toString().isNotEmpty()
                && editHeight.text.toString().isNotEmpty()
                && editAge.text.toString().isNotEmpty()
                && !editHeight.text.toString().startsWith("0")
                && !editWeight.text.toString().startsWith("0")
                && !editAge.text.toString().startsWith("0"))
    }

    private fun hideKeyboard() {
        val service = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        service.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }

    // inflar o menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    // quando clicar em algum item do menu. precisa identificar qual menu tambem
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_search) {
            finish()
            openListActivity()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun openListActivity() {
        val intent = Intent(this@TmbActivity, ListCalcActivity::class.java)
        intent.putExtra("type", "tmb")
        startActivity(intent)
    }
}