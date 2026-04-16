package pro.branium.learnjetpackcompose.lesson41.ui.chat

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest
import com.google.firebase.storage.FirebaseStorage
import pro.branium.learnjetpackcompose.lesson41.domain.model.Message
import pro.branium.learnjetpackcompose.lesson41.domain.model.User
import pro.branium.learnjetpackcompose.lesson41.ui.extensions.getExtensionFromMime
import pro.branium.learnjetpackcompose.lesson41.ui.extensions.getMimeType
import pro.branium.learnjetpackcompose.lesson41.ui.extensions.resolveAttachmentType
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    sender: User,
    receiver: User,
    navController: NavController
) {
    val viewModel: ChatViewModel = viewModel()
    val context = LocalContext.current
    val chatUiState by viewModel.chatUiState.collectAsState()
    val listState = rememberLazyListState()

    var selectedAttachment by remember { mutableStateOf<Uri?>(null) }
    var isUploading by remember { mutableStateOf(false) }

    // Load lần đầu
    LaunchedEffect(Unit) {
        viewModel.getRecentMessages(sender.userId, receiver.userId)
        viewModel.getFriends(sender.userId)
    }

    // Load thêm khi scroll
    LaunchedEffect(listState) {
        snapshotFlow {
            listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
        }.collect { lastVisibleIndex ->
            val total = chatUiState.messages.size
            val lastMessage = chatUiState.messages.lastOrNull()
            if (lastVisibleIndex != null && lastVisibleIndex >= total - 5) {
                viewModel.getRecentMessages(
                    sender.userId,
                    receiver.userId,
                    lastMessage?.createdAt
                )
            }
        }
    }

    // Auto scroll
    LaunchedEffect(chatUiState.messages.size) {
        if (chatUiState.messages.isNotEmpty()) {
            listState.animateScrollToItem(0)
        }
    }

    // Picker
    val onAddAttachment = rememberAttachmentPicker { uri ->
        selectedAttachment = uri
    }

    val onSendMessage: (String) -> Unit = { text ->
        val chatId = createChatId(sender.userId, receiver.userId)
        if (selectedAttachment != null) {
            isUploading = true
            uploadToFirebase(
                context = context,
                uri = selectedAttachment!!,
                chatId = chatId,
                onResult = { url, type ->
                    val finalText = text.ifBlank { "" }
                    val message = Message(
                        messageId = UUID.randomUUID().toString(),
                        senderId = sender.userId,
                        receiverId = receiver.userId,
                        text = finalText,
                        senderName = sender.fullName,
                        isRead = false,
                        createdAt = System.currentTimeMillis(),
                        attachmentUrl = url,
                        attachmentType = type
                    )

                    viewModel.sendMessage(context, message)
                    Log.e("==>", "Send message after upload: $message")
                    selectedAttachment = null
                    isUploading = false
                },
                onError = {
                    Log.e("==>", "Error: ${it.message}")
                    isUploading = false
                }
            )
        } else {
            val message = Message(
                messageId = UUID.randomUUID().toString(),
                senderId = sender.userId,
                receiverId = receiver.userId,
                text = text,
                senderName = sender.fullName,
                isRead = false,
                createdAt = System.currentTimeMillis(),
                attachmentUrl = null,
                attachmentType = null
            )
            Log.e("==>", "Send message without attachment: $message")
            viewModel.sendMessage(context, message)
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(receiver.fullName) },
                navigationIcon = {
                    IconButton({ navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            if (chatUiState.isLoading) {
                Box(Modifier.fillMaxSize(), Alignment.Center) {
                    CircularProgressIndicator()
                }
            } else {
                LazyColumn(
                    state = listState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(innerPadding),
                    reverseLayout = true,
                    contentPadding = PaddingValues(8.dp)
                ) {
                    itemsIndexed(
                        chatUiState.messages,
                        key = { _, item -> item.messageId }
                    ) { _, message ->
                        MessageItem(
                            message = message,
                            isMe = message.senderId == sender.userId,
                            receiverAvatar = ""
                        )
                    }
                }
            }

            // Attachment preview + input
            Column {
                selectedAttachment?.let { uri ->
                    AsyncImage(
                        model = uri,
                        contentDescription = null,
                        modifier = Modifier
                            .size(width = 160.dp, height = 120.dp)
                            .padding(4.dp)
                    )
                }

                if (isUploading) {
                    LinearProgressIndicator(Modifier.fillMaxWidth())
                }
//                ChatInputContainer(
//                    selectedAttachment = selectedAttachment,
//                    isUploading = isUploading,
//                    onSendMessage = onSendMessage,
//                    onAddAttachment = onAddAttachment,
//                    onTakePhoto = {}
//                )
                ChatInputBar(
                    onSendMessage = onSendMessage,
                    onAddAttachment = onAddAttachment,
                    onTakePhoto = {},
                    hasAttachment = selectedAttachment != null
                )
            }
        }
    }
}

@Composable
fun rememberAttachmentPicker(
    onPicked: (Uri) -> Unit
): () -> Unit {
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        uri?.let(onPicked)
    }

    return {
        launcher.launch(
            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
        )
    }
}


/**
 * Hàm tạo chatId dựa trên senderId và receiverId.
 * Nếu senderId <= receiverId, chatId sẽ là "$senderId_$receiverId".
 * Nếu senderId > receiverId, chatId sẽ là "$receiverId_$senderId".
 *
 * @param senderId Id của người gửi.
 * @param receiverId Id của người nhận.
 *
 * @return Chuỗi chatId.
 */
private fun createChatId(senderId: String, receiverId: String): String {
    return if (senderId <= receiverId) {
        "${senderId}_${receiverId}"
    } else {
        "${receiverId}_${senderId}"
    }
}

fun uploadToFirebase(
    context: Context,
    uri: Uri,
    chatId: String,
    onResult: (url: String, type: String) -> Unit,
    onError: (Exception) -> Unit
) {
    val mimeType = getMimeType(context, uri)
    val type = resolveAttachmentType(mimeType)

    val ulid = de.huxhorn.sulky.ulid.ULID().nextULID()
    val extension = getExtensionFromMime(mimeType)

    val folder = when (type) {
        "image" -> "images"
        "video" -> "videos"
        else -> "others"
    }

    val path = "$folder/$chatId/$ulid.$extension"

    val storageRef = FirebaseStorage.getInstance().reference.child(path)

    storageRef.putFile(uri)
        .continueWithTask { task ->
            if (!task.isSuccessful) throw task.exception!!
            storageRef.downloadUrl
        }
        .addOnSuccessListener { downloadUri ->
            onResult(downloadUri.toString(), type)
        }
        .addOnFailureListener {
            onError(it)
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
            isMe = isMe,
            attachmentUrl = message.attachmentUrl,
            attachmentType = message.attachmentType
        )
    }
}

@Composable
fun ChatBubble(
    text: String,
    isMe: Boolean,
    attachmentUrl: String?,
    attachmentType: String?,
) {
    val bubbleColor = if (isMe) Color(0xFF0084FF) else Color(0xFFE5E5EA)
    val textColor = if (isMe) Color.White else Color.Black

    Row(verticalAlignment = Alignment.Bottom) {

        if (!isMe) {
            BubbleTail(isMe = false)
        }

        Column(
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
                .padding(4.dp)
        ) {
            // 🔥 1. Hiển thị ảnh nếu có
            if (attachmentUrl != null && attachmentType == "image") {
                SubcomposeAsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(attachmentUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    modifier = Modifier
                        .size(200.dp)
                        .clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.Crop
                ) {
                    when (painter.state) {
                        is AsyncImagePainter.State.Loading -> {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color.LightGray),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator(
                                    strokeWidth = 2.dp,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        }

                        is AsyncImagePainter.State.Error -> {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color.Gray),
                                contentAlignment = Alignment.Center
                            ) {
                                Text("Image error", color = Color.White)
                            }
                        }

                        else -> {
                            SubcomposeAsyncImageContent()
                        }
                    }
                }
            }

            // 🔥 2. Hiển thị text nếu có
            if (text.isNotBlank()) {
                Text(
                    text = text,
                    color = textColor,
                    modifier = Modifier.padding(
                        horizontal = 8.dp,
                        vertical = if (attachmentUrl != null) 4.dp else 8.dp
                    )
                )
            }
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
