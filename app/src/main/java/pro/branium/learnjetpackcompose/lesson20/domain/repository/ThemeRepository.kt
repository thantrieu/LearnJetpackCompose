package pro.branium.learnjetpackcompose.lesson20.domain.repository

import kotlinx.coroutines.flow.Flow

interface ThemeRepository {
    val isDarkMode: Flow<Boolean>

    suspend fun setDarkMode(isDarkMode: Boolean)
}
