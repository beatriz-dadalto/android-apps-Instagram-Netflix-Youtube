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

/*
   todas variaveis que tiverem o tipo String poderao usar a funcao toDate()
 */
fun String.toDate(): Date =
   SimpleDateFormat("yyyy-mm-dd", Locale("pt", "BR")).parse(this)

/*
   Design Pattern Builder
 */
class VideoBuilder {
   var id: String = ""
   var thumbnailUrl: String = ""
   var title: String = ""
   var viewsCount: Long = 0
   var publishedAt: Date = Date()
   var viewsCountLabel: String = ""
   var duration: Int = 0
   var videoUrl: String = ""
   var publisher: Publisher = PublisherBuilder().build()

   fun build(): Video = Video(
      id,
      thumbnailUrl,
      title,
      viewsCount,
      publishedAt,
      viewsCountLabel,
      duration,
      videoUrl,
      publisher
   )

   fun publisher(block: PublisherBuilder.() -> Unit): Publisher =
      PublisherBuilder().apply(block).build()
}

class PublisherBuilder {
   var id: String = ""
   var name: String = ""
   var pictureProfileUrl: String = ""

   fun build(): Publisher = Publisher(id, name, pictureProfileUrl)
}

/*
   A Domain Specific Language is a programming language with a
   higher level of abstraction optimized for a specific class of problems.
   A DSL uses the concepts and rules from the field or domain.

   Quando eu instanciar o VideoBuilder ele vai passar o block pra dentro do apply
   passando todas as props da fun build dentro das classes
 */
fun video(block: VideoBuilder.() -> Unit): Video = VideoBuilder().apply(block).build()

fun videos(): List<Video> {
   return arrayListOf(
      video {
         id = "UVpKBHO2fMg"
         thumbnailUrl = "https://img.youtube.com/vi/UVpKBHO2fMg/maxresdefault.jpg"
         title = "Entrevista com Marlon Wayans | The Noite"
         publishedAt = "2019-08-15".toDate()
         viewsCountLabel = "7M"
         duration = 1886
         publisher {
            id = "sbtthenoite"
            name = "The Noite com Danilo Gentili"
            pictureProfileUrl =
               "https://yt3.ggpht.com/a/AGF-l7_3BYlSlp94WOjGe1UECUCdb73qRJVFH_t9Tw=s48-c-k-c0xffffffff-no-rj-mo"
         }
      },
      video {
         id = "PlYUZU0H5go"
         thumbnailUrl = "https://img.youtube.com/vi/cuau8E6t2QU/maxresdefault.jpg"
         title = "Relembrando Steve Jobs"
         publishedAt = "2019-08-28".toDate()
         viewsCountLabel = "5k"
         duration = 194
         publisher {
            id = "UCpJN7kiUkDrH11p0GQhLyFw"
            name = "Movie Trailer Source"
            pictureProfileUrl =
               "https://yt3.ggpht.com/a/AGF-l7_Qmltcncwt0z_XzAzjxnuE5gVV9uj7zThg2w=s48-c-k-c0xffffffff-no-rj-mo"
         }
      }
   )
}