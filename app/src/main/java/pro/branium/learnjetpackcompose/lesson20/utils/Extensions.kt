package pro.branium.learnjetpackcompose.lesson20.utils

import android.content.Context
import android.content.Intent
import pro.branium.learnjetpackcompose.lesson20.media.MusicService

fun Context.sendPlayerCommand(action: String, url: String? = null) {
    val intent = Intent(this, MusicService::class.java).apply {
        setAction(action)
        putExtra("sourceUrl", url)
    }
    startService(intent)
}