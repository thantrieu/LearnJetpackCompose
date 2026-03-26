package pro.branium.learnjetpackcompose.lesson41.ui.chat

import pro.branium.learnjetpackcompose.lesson41.domain.model.Message

data class ChatUiState(
    val messages: List<Message> = emptyList(),
    val isLoading: Boolean = true,
    val error: String? = null
)