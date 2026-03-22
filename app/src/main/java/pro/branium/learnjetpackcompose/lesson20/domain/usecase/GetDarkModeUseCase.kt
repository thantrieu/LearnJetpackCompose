package pro.branium.learnjetpackcompose.lesson20.domain.usecase

import pro.branium.learnjetpackcompose.lesson20.domain.repository.ThemeRepository
import javax.inject.Inject

class GetDarkModeUseCase @Inject constructor(
    private val repository: ThemeRepository
) {
    operator fun invoke() = repository.isDarkMode
}