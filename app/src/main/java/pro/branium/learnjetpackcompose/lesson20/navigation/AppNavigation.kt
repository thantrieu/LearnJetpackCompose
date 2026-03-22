package pro.branium.learnjetpackcompose.lesson20.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pro.branium.learnjetpackcompose.lesson16.HomeScreen
import pro.branium.learnjetpackcompose.lesson16.SongDetailScreen
import pro.branium.learnjetpackcompose.lesson20.ui.MusicPlayerScreen
import pro.branium.learnjetpackcompose.lesson20.ui.SongListScreen
import pro.branium.learnjetpackcompose.lesson20.viewmodel.SongViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun AppNavigation(
    isDark: Boolean = false,
    onDarkThemeChanged: (Boolean) -> Unit = {}
) {
    val songViewModel: SongViewModel = hiltViewModel()
    val navController = rememberNavController()
    SharedTransitionLayout {
        NavHost(navController = navController, startDestination = "home") {
            composable("home") {
                SongListScreen(
                    isDark,
                    navController,
                    songViewModel,
                    onDarkThemeChanged
                )
            }

            composable("details/{songId}") { backStackEntry ->
                val songId = backStackEntry.arguments?.getString("songId")
                songId?.let {
                    MusicPlayerScreen(
                        navController,
                        songViewModel,
                        songId
                    )
                }
            }
        }
    }
}