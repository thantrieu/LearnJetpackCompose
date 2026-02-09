package pro.branium.learnjetpackcompose.lesson35

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FCMService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("FCMService", "token: $token")
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        Log.d("FCMService", "data: ${message.data}")
        Log.d("FCMService", "title: ${message.notification?.title}")
        Log.d("FCMService", "imageUrl: ${message.notification?.imageUrl}")
        Log.d("FCMService", "body: ${message.notification?.body}")
        Log.d("FCMService", "tag: ${message.notification?.tag}")
    }
}