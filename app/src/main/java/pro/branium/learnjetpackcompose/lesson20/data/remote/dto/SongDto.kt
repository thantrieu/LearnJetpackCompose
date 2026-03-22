package pro.branium.learnjetpackcompose.lesson20.data.remote.dto

import com.google.gson.annotations.SerializedName

data class SongDto (
    @SerializedName("id")
    val id: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("album")
    val album: String,

    @SerializedName("artist")
    val artist: String,

    @SerializedName("source")
    val source: String,
    val image: String,
    val duration: Int,
    val favorite: Int,
    val counter: Int,
    @SerializedName("track_number")
    val trackNumber: Int
)