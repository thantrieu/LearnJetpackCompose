package pro.branium.learnjetpackcompose.lesson20.domain.usecase

import kotlinx.coroutines.flow.Flow
import pro.branium.learnjetpackcompose.lesson20.domain.repository.PlaybackStateRepository
import pro.branium.learnjetpackcompose.lesson20.viewmodel.PlayerUiState
import javax.inject.Inject

class PlayerUiStateUseCase @Inject constructor(
    private val repository: PlaybackStateRepository
) {
    operator fun invoke() : Flow<PlayerUiState> = repository.playbackState
}