package pro.branium.learnjetpackcompose.lesson41.domain.model

data class MessagePage(
    val hasMore: Boolean = false,
    val messages: List<Message> = emptyList(),
)
