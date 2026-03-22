package pro.branium.learnjetpackcompose.lesson20.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface SongDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSongs(songs: List<SongEntity>)

    @Query("SELECT * FROM songs")
    fun getAllSongs(): List<SongEntity>

    @Delete
    suspend fun deleteSong(song: SongEntity)

    @Query("DELETE FROM songs")
    suspend fun deleteAllSongs()

    @Query("SELECT * FROM songs WHERE id = :songId")
    fun getSongById(songId: String): SongEntity?

    @Query("SELECT * FROM songs LIMIT :limit OFFSET :offset")
    fun getLimitedSong(limit: Int, offset: Int): List<SongEntity>

    @Update
    suspend fun updateSong(song: SongEntity)
}