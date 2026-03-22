package pro.branium.learnjetpackcompose.lesson20.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.core.content.ContextCompat
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import pro.branium.learnjetpackcompose.lesson20.media.MusicService
import pro.branium.learnjetpackcompose.lesson20.viewmodel.PlayerUiState

fun Context.sendPlayerCommand(action: String, url: String? = null) {
    val intent = Intent(this, MusicService::class.java).apply {
        setAction(action)
        putExtra("sourceUrl", url)
    }
    startService(intent)
}

fun Context.playbackStateFlow(): Flow<PlayerUiState> = callbackFlow {
    val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action != MusicService.ACTION_PLAYBACK_STATE) return

            val isPlaying = intent.getBooleanExtra(MusicService.EXTRA_IS_PLAYING, false)

            trySend(
                PlayerUiState(isPlaying = isPlaying)
            )
        }
    }

    val filter = IntentFilter(MusicService.ACTION_PLAYBACK_STATE)

    // Android 13+ cần RECEIVER_NOT_EXPORTED (vì broadcast nội bộ app)
    ContextCompat.registerReceiver(
        this@playbackStateFlow,
        receiver,
        filter,
        ContextCompat.RECEIVER_NOT_EXPORTED
    )

    awaitClose { unregisterReceiver(receiver) }
}