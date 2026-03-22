package pro.branium.learnjetpackcompose.lesson20.domain.model

data class Song(
    val id: String,
    val title: String,
    val album: String?,
    val artist: String?,
    val sourceUrl: String,
    val imageUrl: String?,
    val durationSec: Int,
    val favorite: Int,
    val playCount: Int,
    val trackNumber: Int
)

