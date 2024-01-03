package com.beatrizdadalto.youtube

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request

class MainActivity : AppCompatActivity() {

   private lateinit var videoAdapter: VideoAdapter

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)

      setContentView(R.layout.activity_main)

      // setar a toolbar do xml no lugar da actionbar padrao
      setSupportActionBar(toolbar)
      supportActionBar?.title = ""

      val videos: MutableList<Video> = mutableListOf()
      videoAdapter = VideoAdapter(videos) {
         println(it)
      }

      rv_main.layoutManager = LinearLayoutManager(this)
      rv_main.adapter = videoAdapter

      // para nao travar a MAIN THREAD ao fazer requisicao a uma API utilizar processo paralelo
      CoroutineScope(Dispatchers.IO).launch {
         val response = async { getVideo() }
         val listVideo = response.await()
         withContext(Dispatchers.Main) {
            listVideo?.let {
               videos.clear()
               videos.addAll(listVideo.data)
               videoAdapter.notifyDataSetChanged()
               progress_recycler.visibility = View.GONE
            }
         }
      }

   }

   override fun onCreateOptionsMenu(menu: Menu?): Boolean {
      menuInflater.inflate(R.menu.main_menu, menu)
      return super.onCreateOptionsMenu(menu)
   }

   /*
      -> buscar dados da API
      -> buscar em uma nova Thread para
         nao travar a Thread Principal e interface grafica. utilizar coroutines(importar no gradle).
    */
   private fun getVideo(): ListVideo? {
      val client = OkHttpClient.Builder().build()
      val request = Request.Builder()
         .get()
         .url("https://tiagoaguiar.co/api/youtube-videos")
         .build()

      return try {
         val response = client.newCall(request).execute()

         if (response.isSuccessful) {
            // parse from JSON to Object
            GsonBuilder().create()
               .fromJson(response.body()?.string(), ListVideo::class.java)
         } else {
            null
         }
      } catch (e: Exception) {
         null
      }
   }
}