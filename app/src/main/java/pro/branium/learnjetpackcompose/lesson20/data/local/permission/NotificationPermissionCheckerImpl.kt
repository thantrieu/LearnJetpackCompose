package pro.branium.learnjetpackcompose.lesson20.data.local.permission

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.content.ContextCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import pro.branium.learnjetpackcompose.lesson20.data.NotificationPermissionChecker
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotificationPermissionCheckerImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : NotificationPermissionChecker {

    override fun hasPermission(): Boolean {
        // Android 12 trở xuống: không có runtime permission cho notifications
        if (Build.VERSION.SDK_INT < 33) return true

        return ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.POST_NOTIFICATIONS
        ) == PackageManager.PERMISSION_GRANTED
    }
}
