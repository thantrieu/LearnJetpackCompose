package pro.branium.learnjetpackcompose.lesson41.ui.chat

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import pro.branium.learnjetpackcompose.lesson41.data.remote.chat.ChatRepository
import pro.branium.learnjetpackcompose.lesson41.data.remote.user.FriendRepository
import pro.branium.learnjetpackcompose.lesson41.domain.model.Message
import pro.branium.learnjetpackcompose.lesson41.domain.model.User

class ChatViewModel(
    private val repository: ChatRepository = ChatRepository(),
    private val friendRepository: FriendRepository = FriendRepository()
) : ViewModel() {
    private val messageMap = mutableMapOf<String, Message>()

    private val _chatUiState = MutableStateFlow(ChatUiState())
    val chatUiState = _chatUiState.asStateFlow()

    private val _friends = MutableStateFlow<List<User>>(emptyList())
    val friends = _friends.asStateFlow()

    init {
        _chatUiState.value = _chatUiState.value.copy(
            isLoading = true
        )
    }

    fun sendMessage(context: Context, message: Message) {
        viewModelScope.launch {
            val success = repository.sendChatMessage(message)
            if (success) {
                Toast.makeText(context, "Gửi tin nhắn thành công", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Gửi tin nhắn thất bại", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun getRecentMessages(
        senderId: String,
        receiverId: String,
        createdAt: Long? = null
    ) {
        viewModelScope.launch {
            val result = repository.getRecentMessages(senderId, receiverId, createdAt)
            _chatUiState.value = _chatUiState.value.copy(
                isLoading = false
            )
            if (result.isSuccess) {
                val page = result.getOrNull()
                page?.let { messagePage ->
                    messagePage.messages.forEach {
                        messageMap[it.messageId] = it
                    }
                    _chatUiState.value = _chatUiState.value.copy(
                        messages = messagePage.messages
                    )
                }
            } else {
                _chatUiState.value = _chatUiState.value.copy(
                    error = result.exceptionOrNull()?.message
                )
            }
        }
    }

    fun getFriends(userId: String) {
        viewModelScope.launch {
            friendRepository.getFriends(userId).collect {
                _friends.value = it
            }
        }
    }
}