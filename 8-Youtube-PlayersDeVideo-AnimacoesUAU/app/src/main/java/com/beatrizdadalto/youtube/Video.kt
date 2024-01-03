package com.beatrizdadalto.youtube

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/*
   mapear igualmente aos dados que vem no JSON da API
 */
data class Video(
   val id: String,
   val thumbnailUrl: String,
   val title: String,
   val viewsCount: Long,
   val publishedAt: Date,
   val viewsCountLabel: String,
   val duration: Int,
   val videoUrl: String,
   val publisher: Publisher
)

data class Publisher(
   val id: String,
   val name: String,
   val pictureProfileUrl: String
)

data class ListVideo(
   val status: Int,
   val data: List<Video>
)

/*
   todas variaveis que tiverem o tipo Date poderao usar a funcao formatted()
 */
fun Date.formatted(): String =
   SimpleDateFormat("d MMM yyyy", Locale("pt", "BR")).format(this)