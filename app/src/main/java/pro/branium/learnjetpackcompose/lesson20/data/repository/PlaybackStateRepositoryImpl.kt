package pro.branium.learnjetpackcompose.lesson20.data.repository

import kotlinx.coroutines.flow.Flow
import pro.branium.learnjetpackcompose.lesson20.data.local.datasource.PlaybackStateDataSource
import pro.branium.learnjetpackcompose.lesson20.domain.repository.PlaybackStateRepository
import pro.branium.learnjetpackcompose.lesson20.viewmodel.PlayerUiState
import javax.inject.Inject

class PlaybackStateRepositoryImpl @Inject constructor(
    private val dataSource: PlaybackStateDataSource
) : PlaybackStateRepository {
    override val playbackState: Flow<PlayerUiState>
        get() = dataSource.playerUIStateFlow
}