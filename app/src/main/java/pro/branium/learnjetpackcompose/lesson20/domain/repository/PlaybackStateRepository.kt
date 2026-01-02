package pro.branium.learnjetpackcompose.lesson20.domain.repository

import kotlinx.coroutines.flow.Flow
import pro.branium.learnjetpackcompose.lesson20.viewmodel.PlayerUiState

interface PlaybackStateRepository {
    val playbackState: Flow<PlayerUiState>
}