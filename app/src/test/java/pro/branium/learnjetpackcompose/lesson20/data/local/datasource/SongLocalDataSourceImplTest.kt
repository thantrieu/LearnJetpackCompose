package pro.branium.learnjetpackcompose.lesson20.data.local.datasource

import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runners.Parameterized
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import pro.branium.learnjetpackcompose.lesson20.data.local.SongDao
import pro.branium.learnjetpackcompose.lesson20.data.local.SongEntity
import kotlin.math.exp

class SongLocalDataSourceImplTest {
    private lateinit var songDao: SongDao
    private lateinit var impl: SongLocalDataSourceImpl

    @Before
    fun setup() {
        songDao = mock(SongDao::class.java)
        impl = SongLocalDataSourceImpl(songDao)
    }

    @Test
    fun givenCorrectParameters_whenGetSongs_thenReturnSongsList() {
        `when`(songDao.getAllSongs()).thenReturn(fakeSongs)
        val result = impl.getLimitedSongs(3, 0)
        val expected = fakeSongs.take(3)
        assertEquals(expected.size, result.size)
        verify(songDao).getAllSongs()
    }

    @Test
    fun givenInCorrectParameters_whenGetSongs_thenReturnEmptySongsList() {
        val limit = -3
        val offset = 0
        `when`(songDao.getAllSongs()).thenReturn(emptyList())
        val result = impl.getLimitedSongs(limit, offset)
        val expected = emptyList<SongEntity>()
        assertEquals(expected.size, result.size)
        verify(songDao).getAllSongs()
    }

    @Test
    fun getSongById() {
    }

    companion object {
        val fakeSongs = listOf(
            SongEntity(
                id = "S1",
                title = "Song 1",
                album = "Album 1",
                artist = "Artist 1",
                sourceUrl = "url1",
                imageUrl = "image1",
                durationSec = 1,
                favorite = 0,
                playCount = 0,
                trackNumber = 0
            ),
            SongEntity(
                id = "S2",
                title = "Song 2",
                album = "Album 1",
                artist = "Artist 1",
                sourceUrl = "url1",
                imageUrl = "image1",
                durationSec = 1,
                favorite = 0,
                playCount = 0,
                trackNumber = 0
            ),
            SongEntity(
                id = "S3",
                title = "Song 3",
                album = "Album 1",
                artist = "Artist 1",
                sourceUrl = "url1",
                imageUrl = "image1",
                durationSec = 1,
                favorite = 0,
                playCount = 0,
                trackNumber = 0
            ),
        )
    }

    @After
    fun tearDown() {

    }
}