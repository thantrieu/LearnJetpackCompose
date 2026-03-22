package pro.branium.learnjetpackcompose.lesson20.data.local.datasource

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import pro.branium.learnjetpackcompose.lesson20.utils.playbackStateFlow
import pro.branium.learnjetpackcompose.lesson20.viewmodel.PlayerUiState
import javax.inject.Inject

class PlaybackStateDataSourceImpl @Inject constructor(
    @ApplicationContext appContext: Context
) : PlaybackStateDataSource {
    private val context: Context = appContext

    override val playerUIStateFlow: Flow<PlayerUiState>
        get() = context.playbackStateFlow()
}