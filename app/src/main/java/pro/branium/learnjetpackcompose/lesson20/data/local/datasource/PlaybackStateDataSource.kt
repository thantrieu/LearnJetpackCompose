package pro.branium.learnjetpackcompose.lesson20.data.local.datasource

import kotlinx.coroutines.flow.Flow
import pro.branium.learnjetpackcompose.lesson20.viewmodel.PlayerUiState

interface PlaybackStateDataSource {
    val playerUIStateFlow: Flow<PlayerUiState>
}