package br.com.beatrizdadalto.layoutsfundamentos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val txtUsername: TextView = findViewById(R.id.txt_username)
        val txtEmail: TextView = findViewById(R.id.txt_email)

        // aqui um monte de codigo que busca em um banco de dados
        val username = "Beatriz Dadalto"

        txtUsername.text = username
        txtEmail.setText(R.string.send)
    }
}