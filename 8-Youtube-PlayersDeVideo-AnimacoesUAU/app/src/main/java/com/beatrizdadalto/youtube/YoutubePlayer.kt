package com.beatrizdadalto.youtube

import android.content.Context
import android.net.Uri
import android.os.Handler
import android.view.SurfaceHolder
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util

class YoutubePlayer(private val context: Context) : SurfaceHolder.Callback {

   private var mediaPlayer: SimpleExoPlayer? = null

   override fun surfaceCreated(holder: SurfaceHolder) {
      if (mediaPlayer == null) {
         mediaPlayer = SimpleExoPlayer.Builder(context).build()
         mediaPlayer?.setVideoSurfaceHolder(holder)
      }
   }

   override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
      // TODO("Not yet implemented")
   }

   override fun surfaceDestroyed(holder: SurfaceHolder) {
      mediaPlayer?.release()
   }

   // o video virá de uma url
   fun setUrl(url: String) {
      mediaPlayer?.let {
         val dataSourceFactory = DefaultDataSourceFactory(
            context, Util.getUserAgent(context, "youtube")
         )

         val videoSource: MediaSource = ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(Uri.parse(url))

         it.prepare(videoSource)
         play()
      }
   }

   fun play() {
      mediaPlayer?.playWhenReady = true
   }

   fun pause() {
      mediaPlayer?.playWhenReady = false
   }

   fun release() {
      mediaPlayer?.release()
   }
}