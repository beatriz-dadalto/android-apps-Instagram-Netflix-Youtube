package co.tiagoaguiar.fitnesstracker

import android.app.Application
import co.tiagoaguiar.fitnesstracker.model.AppDatabase

/*
    Application eh uma classe que eh inicializada sempre que o app inicia
 */
class App : Application() {

    lateinit var db: AppDatabase

    override fun onCreate() {
        super.onCreate()
        db = AppDatabase.getDatabase(this)
    }
}