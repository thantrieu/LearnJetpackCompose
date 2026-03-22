package pro.branium.learnjetpackcompose.lesson20.data.remote.datasource

import pro.branium.learnjetpackcompose.lesson20.data.remote.dto.SongDto

interface SongRemoteDataSource {
    suspend fun getLimitedSongs(limit: Int, offset: Int): List<SongDto>
}