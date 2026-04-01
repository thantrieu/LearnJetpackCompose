package pro.branium.learnjetpackcompose.lesson41.ui.chat

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import pro.branium.learnjetpackcompose.lesson41.domain.model.Message
import pro.branium.learnjetpackcompose.lesson41.domain.model.User

@Composable
fun ChatScreen(
    user: User
) {
    val viewModel: ChatViewModel = viewModel()
    val context = LocalContext.current
    val chatUiState by viewModel.chatUiState.collectAsState()
    val listState = rememberLazyListState()

//    val receiverId = "108694973833202095556"
    val receiverId = "106700342328425688585"

    // load lần đầu
    LaunchedEffect(Unit) {
        Log.e("==>", "Load lần đầu")
        viewModel.getRecentMessages(user.userId, receiverId)
        viewModel.getFriends(user.userId)
    }

    // detect scroll để load thêm
    LaunchedEffect(listState) {
        Log.e("==>", "Detect scroll")
        snapshotFlow {
            listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
        }.collect { lastVisibleIndex ->
            val total = chatUiState.messages.size
            val lastMessage = chatUiState.messages.lastOrNull()
            if (lastVisibleIndex != null && lastVisibleIndex >= total - 5) {
                viewModel.getRecentMessages(user.userId, receiverId, lastMessage?.createdAt)
            }
        }
    }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                reverseLayout = true,
                contentPadding = PaddingValues(8.dp)
            ) {
                Log.e("==>", "Message size: ${chatUiState.messages.size}")
                itemsIndexed(
                    chatUiState.messages,
                    key = { _, item -> item.messageId }
                ) { _, message ->
                    MessageItem(
                        message = message,
                        isMe = message.senderId == user.userId,
                        receiverAvatar = ""
                    )
                }

                if (chatUiState.isLoading) {
                    item {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }
            }

            ChatInputBar(
                onSendMessage = { dataString ->
                    val message = Message(
                        messageId = "",
                        senderId = user.userId,
                        receiverId = receiverId,
                        text = dataString,
                        senderName = user.fullName
                    )

                    viewModel.sendMessage(context = context, message)
                },
                onAddAttachment = { },
                onTakePhoto = { }
            )
        }
    }
}

@Composable
fun MessageItem(
    message: Message,
    isMe: Boolean,
    receiverAvatar: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = if (isMe) Arrangement.End else Arrangement.Start,
        verticalAlignment = Alignment.Bottom
    ) {

        if (!isMe) {
            // Avatar bên trái
            AsyncImage(
                model = receiverAvatar,
                contentDescription = null,
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(6.dp))
        }

        ChatBubble(
            text = message.text,
            isMe = isMe
        )
    }
}

@Composable
fun ChatBubble(
    text: String,
    isMe: Boolean
) {
    val bubbleColor = if (isMe) Color(0xFF0084FF) else Color(0xFFE5E5EA)
    val textColor = if (isMe) Color.White else Color.Black

    Row(verticalAlignment = Alignment.Bottom) {

        if (!isMe) {
            BubbleTail(isMe = false)
        }

        Box(
            modifier = Modifier
                .background(
                    color = bubbleColor,
                    shape = RoundedCornerShape(
                        topStart = 16.dp,
                        topEnd = 16.dp,
                        bottomStart = if (isMe) 16.dp else 4.dp,
                        bottomEnd = if (isMe) 4.dp else 16.dp
                    )
                )
                .padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
            Text(
                text = text,
                color = textColor
            )
        }

        if (isMe) {
            BubbleTail(isMe = true)
        }
    }
}

@Composable
fun BubbleTail(isMe: Boolean) {
    Canvas(
        modifier = Modifier
            .size(8.dp)
    ) {
        val path = Path().apply {
            if (isMe) {
                moveTo(0f, 0f)
                lineTo(size.width, size.height / 2)
                lineTo(0f, size.height)
            } else {
                moveTo(size.width, 0f)
                lineTo(0f, size.height / 2)
                lineTo(size.width, size.height)
            }
            close()
        }

        drawPath(
            path = path,
            color = if (isMe) Color(0xFF0084FF) else Color(0xFFE5E5EA)
        )
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