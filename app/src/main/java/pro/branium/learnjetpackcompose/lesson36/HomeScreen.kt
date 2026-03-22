package pro.branium.learnjetpackcompose.lesson36

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest

// 1. Định nghĩa Data Model cho User
data class UserProfile(
    val uid: String = "",
    val fullName: String,
    val email: String,
    val avatarUrl: String? = null
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(user: UserProfile) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Tổng quan",
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                actions = {
                    IconButton(onClick = { /* Xử lý cài đặt */ }) {
                        Icon(Icons.Default.Settings, contentDescription = "Settings")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { innerPadding ->
        // Bố cục chính
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Lời chào đầu ngày
            Text(
                text = "Chào mừng bạn trở lại,",
                modifier = Modifier.align(Alignment.Start).padding(bottom = 4.dp),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
            )
            Text(
                text = user.fullName.split(" ").last(), // Chỉ lấy tên gọi
                modifier = Modifier.align(Alignment.Start).padding(bottom = 24.dp),
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.primary
            )

            // 2. Thẻ thông tin người dùng (User Info Card) - TRỌNG TÂM THIẾT KẾ
            UserProfileCard(user = user)

            // Có thể thêm các thành phần khác của màn hình Home ở đây
            Spacer(modifier = Modifier.height(24.dp))
            Text("Các hoạt động gần đây", style = MaterialTheme.typography.titleMedium, modifier = Modifier.align(Alignment.Start))
        }
    }
}

@Composable
fun UserProfileCard(user: UserProfile) {
    // Tạo hiệu ứng Gradient nhẹ cho nền thẻ
    val gradientBackground = Brush.linearGradient(
        colors = listOf(
            MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.8f),
            MaterialTheme.colorScheme.surfaceVariant
        )
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp),
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(gradientBackground)
                .padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // 3. Ảnh đại diện (Avatar) - Tròn, có viền
                UserAvatar(avatarUrl = user.avatarUrl)

                Spacer(modifier = Modifier.width(20.dp))

                // 4. Khối thông tin chữ (Name & Email)
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.Center
                ) {
                    InfoLabel(icon = Icons.Default.Person, text = user.fullName, isName = true)
                    Spacer(modifier = Modifier.height(12.dp))
                    InfoLabel(icon = Icons.Default.Email, text = user.email, isName = false)
                }
            }
        }
    }
}

@Composable
fun UserAvatar(avatarUrl: String?) {
    Box(
        modifier = Modifier
            .size(100.dp)
            .clip(CircleShape)
            .border(3.dp, Color.White, CircleShape) // Viền trắng nổi bật
            .background(MaterialTheme.colorScheme.surface),
        contentAlignment = Alignment.Center
    ) {
        if (avatarUrl != null) {
            // Sử dụng Coil để tải ảnh từ URL
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(avatarUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = "User Avatar",
                contentScale = ContentScale.Crop, // Cắt ảnh vừa khung tròn
                modifier = Modifier.fillMaxSize()
            )
        } else {
            // Ảnh mặc định nếu không có URL
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Default Avatar",
                modifier = Modifier.size(60.dp),
                tint = MaterialTheme.colorScheme.outline
            )
        }
    }
}

@Composable
fun InfoLabel(icon: ImageVector, text: String, isName: Boolean) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(if (isName) 22.dp else 18.dp),
            tint = if (isName) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = text,
            style = if (isName) {
                MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
            } else {
                MaterialTheme.typography.bodyMedium
            },
            color = if (isName) {
                MaterialTheme.colorScheme.onSurface
            } else {
                MaterialTheme.colorScheme.onSurfaceVariant
            },
            letterSpacing = if (isName) 0.5.sp else 0.sp
        )
    }
}

// --- Preview để xem nhanh ---
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    MaterialTheme {
        HomeScreen(
            user = UserProfile(
                fullName = "Nguyễn Văn A",
                email = "vanga.nguyen@example.com",
                avatarUrl = "https://example.com/placeholder-avatar.jpg" // Thay bằng URL thật để xem ảnh
            )
        )
    }
}