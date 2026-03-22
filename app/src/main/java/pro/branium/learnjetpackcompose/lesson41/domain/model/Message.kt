package pro.branium.learnjetpackcompose.lesson41.domain.model

data class Message(
    val chatId: String = "",
    val senderId: String,
    val receiverId: String,
    val text: String,
    val senderName: String,
    val isRead: Boolean = false,
    val timestamp: Long = System.currentTimeMillis(),
    val attachmentUrl: String? = null
)