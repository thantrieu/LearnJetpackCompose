package pro.branium.learnjetpackcompose.lesson41.ui

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import pro.branium.learnjetpackcompose.lesson41.domain.model.User
import pro.branium.learnjetpackcompose.lesson41.ui.chat.ChatViewModel

// list friends

@Composable
fun FriendsList(viewModel: ChatViewModel) {
    val friends by viewModel.friends.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            itemsIndexed(
                friends,
                key = { _, item -> item.userId }
            ) { _, friend ->
                FriendItem(friend = friend)
            }
        }
    }

}

// avatar, name, last message, time

@Composable
fun FriendItem(friend: User) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Log.e("==>", "avatar url: ${friend.avatarUrl}")
        // Avatar
        AsyncImage(
            model = friend.avatarUrl,
            contentDescription = null,
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = friend.fullName,
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 1
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "No messages yet",
                style = MaterialTheme.typography.bodySmall,
                maxLines = 1,
                color = Color.Gray
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = "",
            style = MaterialTheme.typography.labelSmall,
            color = Color.Gray
        )
    }
}