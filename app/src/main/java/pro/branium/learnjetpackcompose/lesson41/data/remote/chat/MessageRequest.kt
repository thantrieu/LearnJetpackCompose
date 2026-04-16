package pro.branium.learnjetpackcompose.lesson41.data.remote.chat

data class MessageRequest(
    val senderId: String,
    val receiverId: String,
    val text: String,
    val attachmentUrl: String? = null,
    val attachmentType: String? = null,
)