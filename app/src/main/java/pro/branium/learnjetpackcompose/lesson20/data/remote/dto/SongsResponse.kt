package pro.branium.learnjetpackcompose.lesson20.data.remote.dto

import com.google.gson.annotations.SerializedName

data class SongsResponse(
    @SerializedName("songs")
    val songs: List<SongDto>
)