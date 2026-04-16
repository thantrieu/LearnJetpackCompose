package pro.branium.learnjetpackcompose.lesson41.data.remote.chat

import android.util.Log
import pro.branium.learnjetpackcompose.lesson41.data.remote.ChatApiService
import pro.branium.learnjetpackcompose.lesson41.data.toMessageRequest
import pro.branium.learnjetpackcompose.lesson41.domain.model.Message
import pro.branium.learnjetpackcompose.lesson41.domain.model.MessagePage

class ChatRepository(
    private val api: ChatApiService = ChatRetrofitClient.api
) {
    suspend fun sendChatMessage(message: Message): Boolean {
        val request = message.toMessageRequest()
        Log.e("==>", "$request")
        return try {
            val response = api.sendMessage(request)
            Log.e("==>", "Response: $response")
            response.status == "Success" || response.messageId != null || response.error == null
        } catch (_: Exception) {
            false
        }
    }

    suspend fun getRecentMessages(
        senderId: String,
        receiverId: String,
        createdAt: Long? = null
    ): Result<MessagePage> {
        val request = RecentMessageRequest(
            senderId = senderId,
            receiverId = receiverId,
            createdAt = createdAt
        )
        return try {
            val response = api.getMessages(request)
            Log.e("==>", "Response: $response")
            if (response.success) {
                val page = MessagePage(
                    hasMore = response.hasMore,
                    messages = response.messages
                )
                Result.success(page)
            } else {
                Result.failure(Exception("Lỗi lấy lịch sử trò chuyện"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}