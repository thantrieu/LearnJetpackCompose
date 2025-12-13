package pro.branium.learnjetpackcompose.lesson20.domain.usecase

import pro.branium.learnjetpackcompose.lesson20.domain.repository.ThemeRepository
import javax.inject.Inject

class SetDarkModeUseCase @Inject constructor(
    private val repository: ThemeRepository
) {
    suspend operator fun invoke(isDarkMode: Boolean) {
        repository.setDarkMode(isDarkMode)
    }
}