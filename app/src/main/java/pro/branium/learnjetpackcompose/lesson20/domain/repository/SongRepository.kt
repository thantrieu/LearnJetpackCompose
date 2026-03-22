package pro.branium.learnjetpackcompose.lesson20.domain.repository

import pro.branium.learnjetpackcompose.lesson20.data.remote.ApiResult
import pro.branium.learnjetpackcompose.lesson20.domain.model.Song

interface SongRepository {
    suspend fun getLimitedSongs(limit: Int, offset: Int): ApiResult<List<Song>>
}