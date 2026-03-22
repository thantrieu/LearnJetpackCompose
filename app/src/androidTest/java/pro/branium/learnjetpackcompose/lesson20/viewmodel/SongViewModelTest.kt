package pro.branium.learnjetpackcompose.lesson20.viewmodel

import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import pro.branium.learnjetpackcompose.lesson20.data.remote.ApiResult
import pro.branium.learnjetpackcompose.lesson20.domain.model.Album
import pro.branium.learnjetpackcompose.lesson20.domain.model.Song
import pro.branium.learnjetpackcompose.lesson20.domain.repository.AlbumRepository
import pro.branium.learnjetpackcompose.lesson20.domain.repository.SongRepository
import pro.branium.learnjetpackcompose.lesson20.domain.usecase.GetLimitedAlbumUseCase
import pro.branium.learnjetpackcompose.lesson20.domain.usecase.GetLimitedSongsUseCase
import kotlinx.coroutines.test.advanceUntilIdle

class SongViewModelTest {
    @get:Rule
    val rule = MainDispatcherRule()

    lateinit var viewModel: SongViewModel
    val songRepository: SongRepository = mock()
    val albumRepository: AlbumRepository = mock()

    val getLimitedAlbumUseCase = GetLimitedAlbumUseCase(albumRepository)
    val getLimitedSongsUseCase = GetLimitedSongsUseCase(songRepository)

    val fakeSongs = listOf(
        Song(
            "s1",
            "Song 1",
            "Artist 1",
            "album1",
            "",
            "",
            durationSec = 0,
            favorite = 0,
            playCount = 0,
            trackNumber = 0
        ),
        Song(
            "s2",
            "Song 2",
            "Artist 2",
            "album2",
            "",
            "",
            durationSec = 0,
            favorite = 0,
            playCount = 0,
            trackNumber = 0
        )
    )

    val fakeAlbums = listOf(Album("a1", "Album 1", "Artist 1", ""))

    @Before
    fun setUp() = runTest {
        `when`(songRepository.getLimitedSongs(100, 0))
            .thenReturn(ApiResult.Success(fakeSongs))

        `when`(albumRepository.getLimitedAlbums(100, 0))
            .thenReturn(ApiResult.Success(fakeAlbums))

        viewModel = SongViewModel(
            getLimitedAlbumUseCase = getLimitedAlbumUseCase,
            getLimitedSongsUseCase = getLimitedSongsUseCase
        )
    }

    @After
    fun tearDown() {

    }

    @Test
    fun givenSongList_whenSendValidSongId_thenReturnCorrectSong() {
    }

    @Test
    fun givenAValidSong_whenSendValidSongId_thenReturnCorrectSongState() {
    }

    @Test
    fun getSongsState() {
    }

    @Test
    fun getAlbumsState() {
    }

    @Test
    fun givenNetworkAvailable_whenFetchSong_thenEmitSongs() {
        viewModel.fetchSongs()

        val songState = viewModel.songsState.value
        assertTrue(songState is ApiResult.Success)
        assertEquals(2, (songState as ApiResult.Success).data.size)
    }

    @Test
    fun givenNetworkError_whenFetchSong_thenEmitErrorState() {

    }

    @Test
    fun givenEmptyResponse_whenFetchSong_thenEmitEmptyState() {

    }

    @Test
    fun getSongById() {
    }

}