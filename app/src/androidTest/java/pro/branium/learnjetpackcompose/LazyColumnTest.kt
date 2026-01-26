package pro.branium.learnjetpackcompose

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollToIndex
import androidx.compose.ui.test.performScrollToNode
import androidx.navigation.NavHostController
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import pro.branium.learnjetpackcompose.lesson20.domain.model.Song
import pro.branium.learnjetpackcompose.lesson20.ui.showSongList

@RunWith(AndroidJUnit4::class)
class LazyColumnTest {
    private lateinit var fakeData: List<Song>

    @Before
    fun setup() {
        fakeData = listOf(
            Song(
                id = "1",
                title = "Lost in the Night",
                album = "Midnight Stories",
                artist = "Nova Lane",
                sourceUrl = "https://example.com/audio/lost_in_the_night.mp3",
                imageUrl = "https://example.com/images/midnight_stories.jpg",
                durationSec = 215,
                favorite = 1,
                playCount = 128,
                trackNumber = 1
            ),
            Song(
                id = "2",
                title = "Lost in the Night",
                album = "Midnight Stories",
                artist = "Nova Lane",
                sourceUrl = "https://example.com/audio/lost_in_the_night.mp3",
                imageUrl = "https://example.com/images/midnight_stories.jpg",
                durationSec = 215,
                favorite = 1,
                playCount = 128,
                trackNumber = 1
            ),
            Song(
                id = "3",
                title = "Lost in the Night",
                album = "Midnight Stories",
                artist = "Nova Lane",
                sourceUrl = "https://example.com/audio/lost_in_the_night.mp3",
                imageUrl = "https://example.com/images/midnight_stories.jpg",
                durationSec = 215,
                favorite = 1,
                playCount = 128,
                trackNumber = 1
            ),
            Song(
                id = "4",
                title = "Lost in the Night",
                album = "Midnight Stories",
                artist = "Nova Lane",
                sourceUrl = "https://example.com/audio/lost_in_the_night.mp3",
                imageUrl = "https://example.com/images/midnight_stories.jpg",
                durationSec = 215,
                favorite = 1,
                playCount = 128,
                trackNumber = 1
            ),
            Song(
                id = "5",
                title = "Lost in the Night",
                album = "Midnight Stories",
                artist = "Nova Lane",
                sourceUrl = "https://example.com/audio/lost_in_the_night.mp3",
                imageUrl = "https://example.com/images/midnight_stories.jpg",
                durationSec = 215,
                favorite = 1,
                playCount = 128,
                trackNumber = 1
            ),
            Song(
                id = "6",
                title = "Lost in the Night",
                album = "Midnight Stories",
                artist = "Nova Lane",
                sourceUrl = "https://example.com/audio/lost_in_the_night.mp3",
                imageUrl = "https://example.com/images/midnight_stories.jpg",
                durationSec = 215,
                favorite = 1,
                playCount = 128,
                trackNumber = 1
            ),
            Song(
                id = "7",
                title = "Lost in the Night",
                album = "Midnight Stories",
                artist = "Nova Lane",
                sourceUrl = "https://example.com/audio/lost_in_the_night.mp3",
                imageUrl = "https://example.com/images/midnight_stories.jpg",
                durationSec = 215,
                favorite = 1,
                playCount = 128,
                trackNumber = 1
            ),
            Song(
                id = "8",
                title = "City Lights",
                album = "Urban Dreams",
                artist = "Echo Pulse",
                sourceUrl = "https://example.com/audio/city_lights.mp3",
                imageUrl = "https://example.com/images/urban_dreams.jpg",
                durationSec = 189,
                favorite = 0,
                playCount = 342,
                trackNumber = 2
            ),
            Song(
                id = "9",
                title = "Beyond the Horizon",
                album = "Wanderlust",
                artist = "Skybound",
                sourceUrl = "https://example.com/audio/beyond_the_horizon.mp3",
                imageUrl = "https://example.com/images/wanderlust.jpg",
                durationSec = 242,
                favorite = 1,
                playCount = 87,
                trackNumber = 3
            ),
            Song(
                id = "10",
                title = "Silent Waves",
                album = "Ocean Echoes",
                artist = "Blue Tide",
                sourceUrl = "https://example.com/audio/silent_waves.mp3",
                imageUrl = "https://example.com/images/ocean_echoes.jpg",
                durationSec = 201,
                favorite = 0,
                playCount = 56,
                trackNumber = 4
            ),
            Song(
                id = "11",
                title = "Last Sunrise",
                album = "New Beginnings",
                artist = "Aurora Fields",
                sourceUrl = "https://example.com/audio/last_sunrise.mp3",
                imageUrl = "https://example.com/images/new_beginnings.jpg",
                durationSec = 224,
                favorite = 0,
                playCount = 19,
                trackNumber = 5
            )
        )
        navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        ).apply {
            navigatorProvider.addNavigator(ComposeNavigator())
        }
    }

    lateinit var navController: NavHostController

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    // hàm test hiển thị danh sách
    @Test
    fun givenSongData_whenAppLoaded_thenShowSongList() {
        composeTestRule.setContent {
            showSongList(
                fakeData,
                navController = navController,
            )
        }

        // LazyColumn hiển thị
        composeTestRule
            .onNodeWithTag("song_list")
            .assertIsDisplayed()

        // Có ít nhất 1 bài hát
        composeTestRule
            .onNodeWithTag("SongItem_5")
            .assertExists()
            .assertIsDisplayed()

        // Text bài hát hiển thị
        composeTestRule
            .onNodeWithText("Lost in the Night")
            .assertIsDisplayed()
    }


    // hàm test cuộn tới phần tử có id cho trước
    @Test
    fun givenSongList_whenScrollToSong_thenShowCorrectSong() {
        composeTestRule.setContent {
            showSongList(
                fakeData,
                navController = navController,
            )
        }

        // LazyColumn hiển thị
        composeTestRule
            .onNodeWithTag("song_list")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag("song_list")
            .performScrollToNode(hasText("Silent Waves"))
//            .performScrollToIndex(9)

        composeTestRule
            .onNodeWithText("Silent Waves")
            .assertIsDisplayed()
    }

    // hàm test nhấn vào phần tử
    @Test
    fun givenSongList_whenSongClick_thenShowSongDetails() {

    }
}