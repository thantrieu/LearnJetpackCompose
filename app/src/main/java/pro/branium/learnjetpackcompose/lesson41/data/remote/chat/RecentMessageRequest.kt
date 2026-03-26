package pro.branium.learnjetpackcompose.lesson41.data.remote.chat

import com.google.gson.annotations.SerializedName
import pro.branium.learnjetpackcompose.lesson41.domain.model.Message

data class RecentMessageRequest(
    val senderId: String,
    val receiverId: String,
    val createdAt: Long? = null
)

data class RecentMessageResponse(
    val success: Boolean,
    val chatId: String,
    @SerializedName("data")
    val messages: List<Message> = emptyList(),
    val hasMore: Boolean = false,
)
