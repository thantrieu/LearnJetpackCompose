package pro.branium.learnjetpackcompose.lesson20.viewmodel

import android.os.Build
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import pro.branium.learnjetpackcompose.lesson20.data.NotificationPermissionChecker
import pro.branium.learnjetpackcompose.lesson20.data.PermissionAskedStore
import pro.branium.learnjetpackcompose.lesson20.domain.usecase.DecideNotificationPermissionStepUseCase
import pro.branium.learnjetpackcompose.lesson20.domain.usecase.PermissionNextStep
import javax.inject.Inject

sealed class PermissionEffect {
    data object RequestPermission : PermissionEffect()
    data object OpenSettings : PermissionEffect()
    data object ShowRationale : PermissionEffect()
    data object Proceed : PermissionEffect()
}

@HiltViewModel
class NotificationPermissionViewModel @Inject constructor(
    private val checker: NotificationPermissionChecker,
    private val askedStore: PermissionAskedStore,
    private val decideStep: DecideNotificationPermissionStepUseCase
) : ViewModel() {

    private val _effect = MutableSharedFlow<PermissionEffect>()
    val effect = _effect.asSharedFlow()

    fun onEnableClick(shouldShowRationale: Boolean) = viewModelScope.launch {
        val askedBefore = askedStore.getAskedBefore()
        val has = checker.hasPermission()

        val step = decideStep.execute(
            sdkInt = Build.VERSION.SDK_INT,
            hasPermission = has,
            askedBefore = askedBefore,
            shouldShowRationale = shouldShowRationale
        )

        when (step) {
            PermissionNextStep.PROCEED -> _effect.emit(PermissionEffect.Proceed)
            PermissionNextStep.REQUEST -> {
                askedStore.setAskedBefore(true)
                _effect.emit(PermissionEffect.RequestPermission)
            }
            PermissionNextStep.SHOW_RATIONALE -> _effect.emit(PermissionEffect.ShowRationale)
            PermissionNextStep.OPEN_SETTINGS -> _effect.emit(PermissionEffect.OpenSettings)
        }
    }

    fun onPermissionResult(granted: Boolean) = viewModelScope.launch {
        if (granted) _effect.emit(PermissionEffect.Proceed)
        else _effect.emit(PermissionEffect.OpenSettings) // hoặc giữ denied UI tùy bạn
    }
}
