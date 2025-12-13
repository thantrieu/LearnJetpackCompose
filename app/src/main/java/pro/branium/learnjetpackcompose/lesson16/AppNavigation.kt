package pro.branium.learnjetpackcompose.lesson16

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pro.branium.learnjetpackcompose.lesson13.SongViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun AppNavigation() {
//    val songViewModel: SongViewModel = viewModel()
    val navController = rememberNavController()
    SharedTransitionLayout {
        NavHost(navController = navController, startDestination = "home") {
            composable("home") {
                HomeScreen(
                    navController,
                    this@SharedTransitionLayout,
                    this@composable
                )
            }

            composable("details/{songId}") { backStackEntry ->
                val songId = backStackEntry.arguments?.getString("songId")
                SongDetailScreen(
                    songId, navController,
                    this@SharedTransitionLayout,
                    this@composable
                )
            }
        }
    }
}