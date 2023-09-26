package co.tiagoaguiar.tutorial.jokerappdev.model

import com.google.gson.annotations.SerializedName

data class Joke(
    // pq SerializedName? pq na api o nome eh diferente. está escrito value
    @SerializedName("value")
    val text: String,

    @SerializedName("icon_url")
    val iconUrl: String
)
