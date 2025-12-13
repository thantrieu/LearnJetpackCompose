package pro.branium.learnjetpackcompose.lesson20.domain.repository

import pro.branium.learnjetpackcompose.lesson20.data.remote.ApiResult
import pro.branium.learnjetpackcompose.lesson20.domain.model.Album

interface AlbumRepository {
    suspend fun getLimitedAlbums(limit: Int, offset: Int): ApiResult<List<Album>>
}