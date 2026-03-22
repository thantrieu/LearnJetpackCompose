package pro.branium.learnjetpackcompose.lesson20.data.repository

import pro.branium.learnjetpackcompose.lesson20.data.local.datasource.SongLocalDataSource
import pro.branium.learnjetpackcompose.lesson20.data.remote.datasource.SongRemoteDataSource
import pro.branium.learnjetpackcompose.lesson20.data.mapper.dtoToDomainList
import pro.branium.learnjetpackcompose.lesson20.data.mapper.entityToDomainList
import pro.branium.learnjetpackcompose.lesson20.data.mapper.toListEntity
import pro.branium.learnjetpackcompose.lesson20.data.remote.ApiResult
import pro.branium.learnjetpackcompose.lesson20.domain.model.Song
import pro.branium.learnjetpackcompose.lesson20.domain.repository.SongRepository
import javax.inject.Inject

class SongRepositoryImpl @Inject constructor(
    private val localDataSource: SongLocalDataSource,
    private val remoteDataSource: SongRemoteDataSource
) : SongRepository {
    override suspend fun getLimitedSongs(
        limit: Int,
        offset: Int
    ): ApiResult<List<Song>> {
        val localData = localDataSource.getLimitedSongs(limit, offset)
        if (localData.isEmpty()) {
            val songDtos = remoteDataSource.getLimitedSongs(limit, offset)
            localDataSource.insertSongs(songDtos.toListEntity())
            return ApiResult.Success(songDtos.dtoToDomainList())
        } else {
            return ApiResult.Success(localData.entityToDomainList())
        }
    }
}