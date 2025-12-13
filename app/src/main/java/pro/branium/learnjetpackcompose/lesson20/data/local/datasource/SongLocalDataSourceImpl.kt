package pro.branium.learnjetpackcompose.lesson20.data.local.datasource

import pro.branium.learnjetpackcompose.lesson20.data.local.SongDao
import pro.branium.learnjetpackcompose.lesson20.data.local.SongEntity
import javax.inject.Inject

class SongLocalDataSourceImpl @Inject constructor(
    private val songDao: SongDao
) : SongLocalDataSource {
    override suspend fun insertSongs(songs: List<SongEntity>) {
        songDao.insertSongs(songs)
    }

    override fun getLimitedSongs(
        limit: Int,
        offset: Int
    ): List<SongEntity> {
        return songDao.getAllSongs()
    }

    override fun getSongById(songId: String): SongEntity? {
        return songDao.getSongById(songId)
    }
}