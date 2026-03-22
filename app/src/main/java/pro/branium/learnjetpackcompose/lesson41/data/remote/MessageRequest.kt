package pro.branium.learnjetpackcompose.lesson41.data.remote

data class MessageRequest(
    val senderId: String,
    val receiverId: String,
    val text: String,
)