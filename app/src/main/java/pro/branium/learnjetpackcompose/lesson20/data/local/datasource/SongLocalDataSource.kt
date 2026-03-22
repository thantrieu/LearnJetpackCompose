package pro.branium.learnjetpackcompose.lesson20.data.local.datasource

import pro.branium.learnjetpackcompose.lesson20.data.local.SongEntity

interface SongLocalDataSource {
    suspend fun insertSongs(songs: List<SongEntity>)

    fun getLimitedSongs(limit: Int, offset: Int): List<SongEntity>

    fun getSongById(songId: String): SongEntity?
}