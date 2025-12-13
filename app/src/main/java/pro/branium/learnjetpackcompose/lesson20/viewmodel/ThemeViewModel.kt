package pro.branium.learnjetpackcompose.lesson20.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import pro.branium.learnjetpackcompose.lesson20.domain.usecase.GetDarkModeUseCase
import pro.branium.learnjetpackcompose.lesson20.domain.usecase.SetDarkModeUseCase
import javax.inject.Inject

@HiltViewModel
class ThemeViewModel @Inject constructor(
    getDarkModeUseCase: GetDarkModeUseCase,
    private val setDarkModeUseCase: SetDarkModeUseCase
) : ViewModel() {
    val isDarkMode: Flow<Boolean> = getDarkModeUseCase()

    fun setDarkMode(isDarkMode: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            setDarkModeUseCase(isDarkMode)
        }
    }
}