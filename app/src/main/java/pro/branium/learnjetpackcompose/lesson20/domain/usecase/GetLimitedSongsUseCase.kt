package pro.branium.learnjetpackcompose.lesson20.domain.usecase

import pro.branium.learnjetpackcompose.lesson20.data.remote.ApiResult
import pro.branium.learnjetpackcompose.lesson20.domain.model.Song
import pro.branium.learnjetpackcompose.lesson20.domain.repository.SongRepository
import javax.inject.Inject

class GetLimitedSongsUseCase @Inject constructor(
    private val repository: SongRepository
) {
    suspend operator fun invoke(limit: Int, offset: Int): ApiResult<List<Song>> {
        return repository.getLimitedSongs(limit, offset)
    }
}