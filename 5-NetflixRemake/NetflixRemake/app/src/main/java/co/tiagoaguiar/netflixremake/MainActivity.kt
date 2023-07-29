package co.tiagoaguiar.netflixremake

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        Log.i("TESTE", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("TESTE", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("TESTE", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("TESTE", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("TESTE", "onDestroy")
    }
}