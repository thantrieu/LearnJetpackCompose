package pro.branium.learnjetpackcompose.lesson20.ui

import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import pro.branium.learnjetpackcompose.lesson20.viewmodel.NotificationPermissionViewModel
import pro.branium.learnjetpackcompose.lesson20.viewmodel.PermissionEffect

@Composable
fun NotificationPermissionHandler(
    viewModel: NotificationPermissionViewModel,
    onGranted: () -> Unit,
    onDenied: () -> Unit
) {
    val context = LocalContext.current
    val permission = android.Manifest.permission.POST_NOTIFICATIONS

    var showRationaleDialog by remember { mutableStateOf(false) }

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        viewModel.onPermissionResult(granted)
        if (!granted) onDenied()
    }

    // Rationale dialog (hiện khi ViewModel emit ShowRationale)
    if (showRationaleDialog) {
        AlertDialog(
            onDismissRequest = { showRationaleDialog = false },
            title = { Text("Cho phép thông báo") },
            text = { Text("Ứng dụng cần quyền thông báo để hiển thị điều khiển phát nhạc trên thanh thông báo.") },
            confirmButton = {
                TextButton(
                    onClick = {
                        showRationaleDialog = false
                        if (Build.VERSION.SDK_INT >= 33) launcher.launch(permission) else onGranted()
                    }
                ) { Text("Cho phép") }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showRationaleDialog = false
                        onDenied()
                    }
                ) { Text("Từ chối") }
            }
        )
    }

    LaunchedEffect(Unit) {
        viewModel.effect.collect { eff ->
            when (eff) {
                PermissionEffect.RequestPermission -> {
                    if (Build.VERSION.SDK_INT >= 33) launcher.launch(permission) else onGranted()
                }
                PermissionEffect.ShowRationale -> {
                    showRationaleDialog = true
                }
                PermissionEffect.OpenSettings -> {
                    openAppSettings(context)
                    // Tùy chiến lược: có thể gọi onDenied() tại đây hoặc chờ user quay lại
                }
                PermissionEffect.Proceed -> {
                    onGranted()
                }
            }
        }
    }
}
