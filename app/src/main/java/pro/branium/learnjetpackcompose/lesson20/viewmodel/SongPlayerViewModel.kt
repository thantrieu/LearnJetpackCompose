package pro.branium.learnjetpackcompose.lesson20.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import pro.branium.learnjetpackcompose.lesson20.domain.model.Song
import pro.branium.learnjetpackcompose.lesson20.utils.Action
import pro.branium.learnjetpackcompose.lesson20.utils.sendPlayerCommand
import javax.inject.Inject

data class PlayerUiState(
    val isPlaying: Boolean = false
)

@HiltViewModel
class SongPlayerViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(PlayerUiState())
    val uiState: StateFlow<PlayerUiState> = _uiState.asStateFlow()

    fun playSong(context: Context, song: Song) {
        context.sendPlayerCommand(action = Action.PLAY.name, song.sourceUrl)
        _uiState.value = PlayerUiState(isPlaying = true)
    }

    fun pauseSong(context: Context) {
        context.sendPlayerCommand(action = Action.PAUSE.name)
        _uiState.value = PlayerUiState(isPlaying = false)
    }

    fun stopSong(context: Context) {
        context.sendPlayerCommand(action = Action.STOP.name)
    }
}