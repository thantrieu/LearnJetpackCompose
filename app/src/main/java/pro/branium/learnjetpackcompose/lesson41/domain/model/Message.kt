package pro.branium.learnjetpackcompose.lesson41.domain.model

data class Message(
    val messageId: String = "",
    val senderId: String,
    val receiverId: String,
    val text: String,
    val senderName: String,
    val isRead: Boolean = false,
    val createdAt: Long = System.currentTimeMillis(),
    val attachmentUrl: String? = null,
    val attachmentType: String? = null,
)