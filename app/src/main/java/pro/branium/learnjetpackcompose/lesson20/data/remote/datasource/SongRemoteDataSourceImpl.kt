package pro.branium.learnjetpackcompose.lesson20.data.remote.datasource

import pro.branium.learnjetpackcompose.lesson20.data.remote.dto.SongDto
import pro.branium.learnjetpackcompose.lesson20.data.remote.SongApi
import javax.inject.Inject

class SongRemoteDataSourceImpl @Inject constructor(
    private val songApi: SongApi
): SongRemoteDataSource {
    override suspend fun getLimitedSongs(
        limit: Int,
        offset: Int
    ): List<SongDto> {
        val response = songApi.getLimitedSongs(limit, offset)
        if (response.isSuccessful) {
            return response.body()?.songs ?: emptyList()
        } else {
            throw Exception("Failed to fetch songs")
        }
    }
}