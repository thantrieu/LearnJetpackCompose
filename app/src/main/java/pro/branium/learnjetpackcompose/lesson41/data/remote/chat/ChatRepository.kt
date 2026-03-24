package pro.branium.learnjetpackcompose.lesson41.data.remote.chat

import android.util.Log
import pro.branium.learnjetpackcompose.lesson41.data.remote.ChatApiService
import pro.branium.learnjetpackcompose.lesson41.data.toMessageRequest
import pro.branium.learnjetpackcompose.lesson41.domain.model.Message

class ChatRepository(
    private val api: ChatApiService = ChatRetrofitClient.api
) {
    suspend fun sendChatMessage(message: Message): Boolean {
        val request = message.toMessageRequest()
        Log.e("==>", "$request")
        return try {
            val response = api.sendMessage(request)
            Log.e("==>", "Response: $response")
            response.success
        } catch (_: Exception) {
            false
        }
    }
}