package pro.branium.learnjetpackcompose.lesson20.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import pro.branium.learnjetpackcompose.lesson20.domain.model.Song
import pro.branium.learnjetpackcompose.lesson20.domain.usecase.PlayerUiStateUseCase
import pro.branium.learnjetpackcompose.lesson20.utils.Action
import pro.branium.learnjetpackcompose.lesson20.utils.sendPlayerCommand
import javax.inject.Inject

data class PlayerUiState(
    val isPlaying: Boolean = false
)

@HiltViewModel
class SongPlayerViewModel @Inject constructor(
    playbackStateUseCase: PlayerUiStateUseCase
) : ViewModel() {
    val uiState: StateFlow<PlayerUiState> = playbackStateUseCase.invoke()
        .distinctUntilChanged()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = PlayerUiState()
        )

    fun playSong(context: Context, song: Song) {
        context.sendPlayerCommand(action = Action.PLAY.name, song.sourceUrl)
    }

    fun pauseSong(context: Context) {
        context.sendPlayerCommand(action = Action.PAUSE.name)
    }

    fun stopSong(context: Context) {
        context.sendPlayerCommand(action = Action.STOP.name)
    }
}