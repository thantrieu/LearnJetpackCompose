package pro.branium.learnjetpackcompose.lesson20.data.local.datasource

import kotlinx.coroutines.flow.Flow

interface ThemeLocalDataSource {
    val isDarkMode: Flow<Boolean>

    suspend fun setDarkMode(isDarkMode: Boolean)
}