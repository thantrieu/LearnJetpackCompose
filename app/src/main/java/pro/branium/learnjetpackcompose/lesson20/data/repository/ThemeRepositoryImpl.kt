package pro.branium.learnjetpackcompose.lesson20.data.repository

import kotlinx.coroutines.flow.Flow
import pro.branium.learnjetpackcompose.lesson20.data.local.datasource.ThemeLocalDataSource
import pro.branium.learnjetpackcompose.lesson20.domain.repository.ThemeRepository
import javax.inject.Inject

class ThemeRepositoryImpl @Inject constructor(
    private val dataSource: ThemeLocalDataSource
) : ThemeRepository {
    override val isDarkMode: Flow<Boolean>
        get() = dataSource.isDarkMode

    override suspend fun setDarkMode(isDarkMode: Boolean) {
        dataSource.setDarkMode(isDarkMode)
    }
}