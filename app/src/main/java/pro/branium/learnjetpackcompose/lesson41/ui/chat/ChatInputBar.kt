package pro.branium.learnjetpackcompose.lesson41.ui.chat

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatInputBar(
    onSendMessage: (String) -> Unit,
    onAddAttachment: () -> Unit,
    onTakePhoto: () -> Unit
) {
    var textState by remember { mutableStateOf("") }

    // Màu sắc chủ đạo (có thể thay đổi tùy theo theme ứng dụng)
    val primaryColor = MaterialTheme.colorScheme.primary
    val containerColor = MaterialTheme.colorScheme.surfaceVariant // Màu nền nhẹ cho ô nhập

    Surface(
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = 2.dp, // Tạo shadow nhẹ phân biệt với nội dung chat
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // --- Bên trái: Các nút chức năng đính kèm ---

            // Nút Thêm (+) - Ảnh, File
            IconButton(onClick = onAddAttachment) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Thêm tệp đính kèm",
                    tint = primaryColor
                )
            }

            // Nút Camera
            IconButton(onClick = onTakePhoto) {
                Icon(
                    imageVector = Icons.Default.CameraAlt,
                    contentDescription = "Chụp ảnh",
                    tint = primaryColor
                )
            }

            Spacer(modifier = Modifier.width(4.dp))

            // --- Ở giữa: Ô nhập nội dung text ---

            Surface(
                color = containerColor,
                shape = RoundedCornerShape(24.dp), // Bo góc tròn cho ô nhập
                modifier = Modifier
                    .weight(1f) // Chiếm toàn bộ không gian còn lại
                    .heightIn(min = 40.dp) // Chiều cao tối thiểu
            ) {
                Box(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.CenterStart
                ) {
                    // BasicTextField cho phép tùy chỉnh giao diện ô nhập hoàn toàn
                    BasicTextField(
                        value = textState,
                        onValueChange = { textState = it },
                        textStyle = TextStyle(
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontSize = 16.sp
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.Sentences,
                            imeAction = ImeAction.Send
                        ),
                        // Placeholder (Text gợi ý)
                        decorationBox = { innerTextField ->
                            if (textState.isEmpty()) {
                                Text(
                                    text = "Nhập tin nhắn...",
                                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
                                    fontSize = 16.sp
                                )
                            }
                            innerTextField()
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            // --- Bên phải: Nút Gửi ---

            FilledIconButton(
                onClick = {
                    if (textState.isNotBlank()) {
                        onSendMessage(textState)
                        textState = "" // Xóa nội dung sau khi gửi
                    }
                },
                modifier = Modifier.size(48.dp),
                shape = RoundedCornerShape(24.dp), // Hình tròn cho nút gửi
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = primaryColor,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                enabled = textState.isNotBlank() // Chỉ bật khi có text
            ) {
                Icon(
                    imageVector = Icons.Default.Send,
                    contentDescription = "Gửi tin nhắn",
                    // Xoay icon gửi một chút cho đẹp mắt
                    modifier = Modifier.padding(start = 2.dp)
                )
            }
        }
    }
}