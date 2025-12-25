package pro.branium.learnjetpackcompose.lesson20.media

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import pro.branium.learnjetpackcompose.R
import pro.branium.learnjetpackcompose.lesson20.utils.Action

class MusicService : Service() {
    private lateinit var player: ExoPlayer
    private var isForeground = false

    override fun onCreate() {
        super.onCreate()
        player = ExoPlayer.Builder(this).build()
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                MUSIC_CHANNEL_ID,
                "Music Playback",
                NotificationManager.IMPORTANCE_LOW
            )
            getSystemService(NotificationManager::class.java)
                .createNotificationChannel(channel)
        }
    }

    override fun onBind(intent: Intent): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {

            Action.PLAY.name -> {
                val sourceUrl = intent.getStringExtra("sourceUrl")
                sourceUrl?.let { play(it) }

                if (!isForeground) {
                    startForeground(
                        MUSIC_NOTIFICATION_ID,
                        createNotification(isPlaying = true)
                    )
                    isForeground = true
                } else {
                    updateNotification(isPlaying = true)
                }
            }

            Action.PAUSE.name -> {
                pause()
                updateNotification(isPlaying = false)
            }

            Action.STOP.name -> {
                stopForeground(STOP_FOREGROUND_REMOVE)
                isForeground = false
                stop()
            }
        }
        return START_STICKY
    }

    private fun updateNotification(isPlaying: Boolean) {
        val notification = createNotification(isPlaying)
        val manager = getSystemService(NotificationManager::class.java)
        manager.notify(MUSIC_NOTIFICATION_ID, notification)
    }

    private fun createNotification(isPlaying: Boolean): Notification {
        val playPauseIcon =
            if (isPlaying) R.drawable.ic_pause else R.drawable.ic_play

        return NotificationCompat.Builder(this, MUSIC_CHANNEL_ID)
            .setContentTitle("Music Player")
            .setContentText(if (isPlaying) "Playing" else "Paused")
            .setSmallIcon(R.drawable.ic_song)
            .addAction(
                playPauseIcon,
                if (isPlaying) "Pause" else "Play",
                playPausePendingIntent(isPlaying)
            )
            .setOngoing(true) // vẫn là foreground
            .setOnlyAlertOnce(true)
            .build()
    }

    private fun playPausePendingIntent(isPlaying: Boolean): PendingIntent {
        val action = if (isPlaying) {
            Action.PAUSE.name
        } else {
            Action.PLAY.name
        }

        val intent = Intent(this, MusicService::class.java).apply {
            this.action = action
        }

        return PendingIntent.getService(
            this,
            action.hashCode(), // requestCode khác nhau cho play/pause
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }


    private fun play(sourceUrl: String) {
        val mediaItem = MediaItem.fromUri(sourceUrl)
        player.setMediaItem(mediaItem)
        player.prepare()
        player.play()
    }

    private fun pause() {
        if (player.isPlaying)
            player.pause()
    }

    private fun stop() {
        player.stop()
        stopSelf()
    }

    private fun createNotification(): Notification {
        return NotificationCompat.Builder(this, MUSIC_CHANNEL_ID)
            .setContentTitle("Playing music")
            .setSmallIcon(R.drawable.ic_song)
            .setOngoing(true)
            .build()
    }


    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }

    companion object {
        const val MUSIC_CHANNEL_ID = "music_playback"
        const val MUSIC_NOTIFICATION_ID = 1001
    }
}