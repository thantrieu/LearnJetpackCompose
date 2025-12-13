package pro.branium.learnjetpackcompose.lesson20.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "songs")
data class SongEntity(
    @PrimaryKey
    val id: String,

    val title: String,
    val album: String?,
    val artist: String?,
    val sourceUrl: String,

    @ColumnInfo(name = "image_url")
    val imageUrl: String?,

    val durationSec: Int,
    val favorite: Int,
    val playCount: Int,

    @ColumnInfo(name = "track_number")
    val trackNumber: Int
)