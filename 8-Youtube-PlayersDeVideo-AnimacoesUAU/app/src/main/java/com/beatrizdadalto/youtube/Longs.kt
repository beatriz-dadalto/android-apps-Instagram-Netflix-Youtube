package com.beatrizdadalto.youtube

fun Long.formatTime(): String {
   val minutes = this / 100 / 60
   val seconds = this / 1000 % 60
   return String.format("%02d:%02d", minutes, seconds)
}