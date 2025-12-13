package pro.branium.learnjetpackcompose.lesson20.domain.usecase

import pro.branium.learnjetpackcompose.lesson20.domain.repository.AlbumRepository
import javax.inject.Inject

class GetLimitedAlbumUseCase @Inject constructor(
    private val repository: AlbumRepository
) {
    suspend operator fun invoke(limit: Int, offset: Int) =
        repository.getLimitedAlbums(limit, offset)
}