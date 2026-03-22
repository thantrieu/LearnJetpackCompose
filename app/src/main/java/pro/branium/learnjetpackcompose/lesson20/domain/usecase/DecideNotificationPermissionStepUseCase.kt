package pro.branium.learnjetpackcompose.lesson20.domain.usecase

import javax.inject.Inject

enum class PermissionNextStep {
    PROCEED,           // đã có quyền hoặc không cần xin
    REQUEST,           // xin quyền bằng dialog hệ thống
    SHOW_RATIONALE,    // nên giải thích trước khi xin
    OPEN_SETTINGS      // bị chặn vĩnh viễn -> mở Settings
}

class DecideNotificationPermissionStepUseCase @Inject constructor() {
    fun execute(
        sdkInt: Int,
        hasPermission: Boolean,
        askedBefore: Boolean,
        shouldShowRationale: Boolean
    ): PermissionNextStep {
        if (sdkInt < 33) return PermissionNextStep.PROCEED
        if (hasPermission) return PermissionNextStep.PROCEED

        // đã xin trước đó mà hệ thống không cho rationale -> khả năng cao "Don't ask again"
        if (askedBefore && !shouldShowRationale) return PermissionNextStep.OPEN_SETTINGS

        if (shouldShowRationale) return PermissionNextStep.SHOW_RATIONALE
        return PermissionNextStep.REQUEST
    }
}
