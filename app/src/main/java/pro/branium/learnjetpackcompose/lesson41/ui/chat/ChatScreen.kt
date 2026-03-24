package pro.branium.learnjetpackcompose.lesson41.ui.chat

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import pro.branium.learnjetpackcompose.lesson41.domain.model.Message
import pro.branium.learnjetpackcompose.lesson41.domain.model.User

@Composable
fun ChatScreen(
    user: User
) {
    val viewModel: ChatViewModel = viewModel()
    val context = LocalContext.current

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f), // CHIẾM TOÀN BỘ KHÔNG GIAN CÒN LẠI
                reverseLayout = true, // Thường dùng cho chat để tin nhắn mới nhất ở dưới cùng
                contentPadding = PaddingValues(bottom = 8.dp)
            ) {
                // chứa các tin nhắn
            }
            ChatInputBar(
                onSendMessage = { dataString ->
                    val receiverId = "108694973833202095556" // nguoi nhan
                    // tin nhắn cần gửi đi
                    val message = Message(
                        chatId = "",
                        senderId = user.userId,
                        receiverId = receiverId,
                        text = dataString,
                        senderName = user.fullName
                    )
                    // api: https://sendmessagerest-ircrt6piha-uc.a.run.app/sendMessageRest
                    /**
                     * data mẫu trong body POST method
                     * {
                     *   "chatId": "user001_user002",
                     *   "senderId": "user002",
                     *   "receiverId": "user003",
                     *   "text": "Đây là tin nhắn tiếp theo",
                     *   "senderName": "Branium"
                     * }
                     */
                    // Trong ViewModel hoặc Composable
                    viewModel.sendMessage(context = context, message)
                },
                onAddAttachment = { },
                onTakePhoto = { }
            )
        }
    }
}

fun generateChatId(userId1: String, userId2: String): String {
    return if (userId1 < userId2) {
        "${userId1}_${userId2}"
    } else {
        "${userId2}_${userId1}"
    }
}

@Preview
@Composable
fun ChatScreenPreview() {
    ChatScreen(
        user = User(
            userId = "user001",
            fullName = "Branium",
            email = "",
            avatarUrl = null
        )
    )
}