package pro.branium.learnjetpackcompose.lesson41.ui.extensions

import android.content.Context
import android.net.Uri

fun getMimeType(context: Context, uri: Uri): String? {
    return context.contentResolver.getType(uri)
}

fun getExtensionFromMime(mimeType: String?): String {
    return mimeType?.substringAfter("/") ?: "jpg"
}

fun resolveAttachmentType(mimeType: String?): String {
    return when {
        mimeType?.startsWith("image/") == true -> "image"
        mimeType?.startsWith("video/") == true -> "video"
        else -> "other"
    }
}