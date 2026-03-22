package pro.branium.learnjetpackcompose.lesson13

import kotlinx.serialization.SerialName

data class SongDto(
    @SerialName("id")
    val id: String,

    @SerialName("title")
    val title: String,

    @SerialName("album")
    val album: String,

    @SerialName("artist")
    val artist: String,

    @SerialName("source")
    val source: String,

    @SerialName("image")
    val image: String,

    @SerialName("duration")
    val duration: Int,

    @SerialName("favorite")
    val favorite: Int,

    @SerialName("counter")
    val counter: Int,

    @SerialName("replay")
    val replay: Int
)

data class SongListDto(
    @SerialName("songs")
    val songs: List<SongDto> = emptyList()
)
