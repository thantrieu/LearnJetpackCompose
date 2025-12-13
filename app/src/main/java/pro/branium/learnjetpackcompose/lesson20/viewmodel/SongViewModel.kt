package pro.branium.learnjetpackcompose.lesson20.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pro.branium.learnjetpackcompose.lesson20.data.remote.ApiResult
import pro.branium.learnjetpackcompose.lesson20.domain.model.Album
import pro.branium.learnjetpackcompose.lesson20.domain.model.Song
import pro.branium.learnjetpackcompose.lesson20.domain.usecase.GetLimitedAlbumUseCase
import pro.branium.learnjetpackcompose.lesson20.domain.usecase.GetLimitedSongsUseCase
import javax.inject.Inject

@HiltViewModel
class SongViewModel @Inject constructor(
    private val getLimitedAlbumUseCase: GetLimitedAlbumUseCase,
    private val getLimitedSongsUseCase: GetLimitedSongsUseCase
) : ViewModel() {
    private val _songsState = MutableStateFlow<ApiResult<List<Song>>>(ApiResult.Loading)
    private val _albumsState = MutableStateFlow<ApiResult<List<Album>>>(ApiResult.Loading)

    val songsState: StateFlow<ApiResult<List<Song>>>
        get() = _songsState

    val albumsState: StateFlow<ApiResult<List<Album>>>
        get() = _albumsState

    init {
        fetchSongs()
    }


    fun fetchSongs() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _songsState.value = getLimitedSongsUseCase(100, 0)
                _albumsState.value = getLimitedAlbumUseCase(100, 0)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}