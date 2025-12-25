package pro.branium.learnjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.flowWithLifecycle
import dagger.hilt.android.AndroidEntryPoint
import pro.branium.learnjetpackcompose.lesson20.navigation.AppNavigation
import pro.branium.learnjetpackcompose.lesson20.ui.SongListScreen
import pro.branium.learnjetpackcompose.lesson20.viewmodel.ThemeViewModel
import pro.branium.learnjetpackcompose.ui.theme.AppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val lifecycleOwner = this
        setContent {
            val themeViewModel: ThemeViewModel = hiltViewModel()

            val isDarkTheme by remember(themeViewModel.isDarkMode, lifecycleOwner) {
                themeViewModel.isDarkMode.flowWithLifecycle(lifecycleOwner.lifecycle)
            }.collectAsState(initial = false) // initial = false hoặc isSystemInDarkTheme()

            val onDarkThemeChanged: (Boolean) -> Unit = { newStatus ->
                themeViewModel.setDarkMode(newStatus) // LƯU VÀO DATASTORE
            }

            AppTheme(darkTheme = isDarkTheme, dynamicColor = false) {
                AppNavigation(isDarkTheme, onDarkThemeChanged)
            }
        }
    }
}
