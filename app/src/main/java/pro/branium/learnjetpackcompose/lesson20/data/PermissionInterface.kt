package pro.branium.learnjetpackcompose.lesson20.data

interface NotificationPermissionChecker {
    fun hasPermission(): Boolean
}

interface PermissionAskedStore {
    suspend fun getAskedBefore(): Boolean
    suspend fun setAskedBefore(value: Boolean)
}
