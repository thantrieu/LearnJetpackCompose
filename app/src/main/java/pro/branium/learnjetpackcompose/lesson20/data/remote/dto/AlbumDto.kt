package pro.branium.learnjetpackcompose.lesson20.data.remote.dto

import com.google.gson.annotations.SerializedName

data class AlbumDto(
    @SerializedName("id")
    val id: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("artwork")
    val artwork: String,

    @SerializedName("size")
    val size: String
)

data class AlbumResponse(
    @SerializedName("albums")
    val albums: List<AlbumDto>
)
