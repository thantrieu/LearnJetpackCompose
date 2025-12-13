package pro.branium.learnjetpackcompose.lesson20.data.repository

import pro.branium.learnjetpackcompose.lesson20.data.remote.AlbumApi
import pro.branium.learnjetpackcompose.lesson20.data.remote.ApiResult
import pro.branium.learnjetpackcompose.lesson20.data.remote.RequestParam
import pro.branium.learnjetpackcompose.lesson20.domain.model.Album
import pro.branium.learnjetpackcompose.lesson20.domain.repository.AlbumRepository
import javax.inject.Inject

class AlbumRepositoryImpl @Inject constructor(
    private val albumApi: AlbumApi
) : AlbumRepository {
    override suspend fun getLimitedAlbums(
        limit: Int,
        offset: Int
    ): ApiResult<List<Album>> {
        // todo
        val param = RequestParam(
            limit = limit,
            offset = offset
        )
        val response = albumApi.getLimitedAlbums(param = param)
        if (response.isSuccessful) {
            val albumDtos = response.body()?.albums ?: emptyList()
            val albumModels = albumDtos.map {
                Album(
                    id = it.id,
                    name = it.name,
                    artwork = it.artwork,
                    size = it.size
                )
            }
            return ApiResult.Success(albumModels)
        } else {
            val errorMessage = response.errorBody()?.string() ?: "Unknown error"
            return ApiResult.Error(errorMessage)
        }
    }
}