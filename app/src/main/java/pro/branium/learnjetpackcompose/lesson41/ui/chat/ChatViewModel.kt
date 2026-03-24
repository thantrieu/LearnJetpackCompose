package pro.branium.learnjetpackcompose.lesson41.ui.chat

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pro.branium.learnjetpackcompose.lesson41.data.remote.chat.ChatRepository
import pro.branium.learnjetpackcompose.lesson41.domain.model.Message

class ChatViewModel(
    private val repository: ChatRepository = ChatRepository()
) : ViewModel() {
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
}