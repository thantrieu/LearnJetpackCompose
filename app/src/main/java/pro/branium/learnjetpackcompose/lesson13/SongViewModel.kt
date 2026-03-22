package pro.branium.learnjetpackcompose.lesson13

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SongViewModel : ViewModel() {
    private val _songs = MutableStateFlow(SongListDto())
    private val _isLoading = MutableStateFlow(false)
    private val _error = MutableStateFlow<String?>(null)
    private val _selectedSong = MutableStateFlow<SongDto?>(null)

    val songs: StateFlow<SongListDto>
        get() = _songs

    val isLoading: StateFlow<Boolean>
        get() = _isLoading

    val error: StateFlow<String?>
        get() = _error

    val selectedSong: StateFlow<SongDto?>
        get() = _selectedSong

    init {
        fetchSongs()
    }

    fun getSongById(songId: String?) {
        songId?.let {
            _selectedSong.value = _songs.value.songs.find { it.id == songId }
        }
    }

    fun fetchSongs() {
        _isLoading.value = true
        _error.value = null

        viewModelScope.launch(Dispatchers.IO) {
            val service = RetrofitHelper.instance()
            val call = service.getAllSongs("allSongs")
            call.enqueue(object : Callback<SongListDto> {
                override fun onResponse(
                    call: Call<SongListDto?>,
                    response: Response<SongListDto?>
                ) {
                    if (response.isSuccessful) {
                        _songs.value = response.body()!!
                    } else {
                        _error.value = response.message()
                    }
                    _isLoading.value = false
                }

                override fun onFailure(
                    call: Call<SongListDto?>,
                    t: Throwable
                ) {
                    _error.value = t.message
                    _isLoading.value = false
                }

            })

        }
    }
}