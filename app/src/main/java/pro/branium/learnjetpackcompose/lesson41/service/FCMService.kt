package pro.branium.learnjetpackcompose.lesson41.service

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pro.branium.learnjetpackcompose.lesson41.domain.model.Message

class FCMService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        saveToken(token)
    }

    private fun saveToken(token: String) {
        Log.e("==>", "Token: $token")
        getSharedPreferences("user", MODE_PRIVATE)
            .edit()
            .putString("fcmToken", token)
            .apply()
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        val messageId = message.data["messageId"] ?: ""
        val senderId = message.data["senderId"] ?: ""
        val receiverId = message.data["receiverId"] ?: ""
        val text = message.data["text"] ?: ""
        val senderName = message.data["senderName"] ?: ""
        val isRead = message.data["isRead"] == "true"
        val createdAt = message.data["createdAt"]?.toLong() ?: System.currentTimeMillis()
        val attachmentUrl = message.data["attachmentUrl"]

        val newMessage = Message(
            messageId,
            senderId,
            receiverId,
            text,
            senderName,
            isRead,
            createdAt,
            attachmentUrl
        )

        CoroutineScope(Dispatchers.IO).launch {
            MessageEventBus.emitNewMessage(newMessage)
        }
        Log.e("==>", newMessage.toString())

//        Log.d("FCMService", "data: ${message.data}")
//        Log.d("FCMService", "sender: ${message.notification?.title}")
//        Log.d("FCMService", "imageUrl: ${message.notification?.imageUrl}")
//        Log.d("FCMService", "content: ${message.notification?.body}")
    }
}