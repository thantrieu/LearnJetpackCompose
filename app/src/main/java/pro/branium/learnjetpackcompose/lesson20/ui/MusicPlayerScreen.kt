package pro.branium.learnjetpackcompose.lesson20.ui

import android.app.Activity
import android.os.Build
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.PauseCircle
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import pro.branium.learnjetpackcompose.lesson20.viewmodel.NotificationPermissionViewModel
import pro.branium.learnjetpackcompose.lesson20.viewmodel.SongPlayerViewModel
import pro.branium.learnjetpackcompose.lesson20.viewmodel.SongViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MusicPlayerScreen(
    navController: NavController,
    songViewModel: SongViewModel,
    songId: String,
) {
    val song by songViewModel.selectedSong.collectAsState()
    songViewModel.getSongById(songId = songId)
    val songPlayerViewModel: SongPlayerViewModel = hiltViewModel()
    val uiState by songPlayerViewModel.uiState.collectAsState()

    val context = LocalContext.current
    val activity = context as Activity
    val permission = android.Manifest.permission.POST_NOTIFICATIONS
    val permissionViewModel: NotificationPermissionViewModel = hiltViewModel()

    // Đảm bảo callback dùng dữ liệu mới nhất khi effect chạy (tránh stale capture)
    val latestSong = rememberUpdatedState(song)
    val latestIsPlaying = rememberUpdatedState(uiState.isPlaying)
    val latestContext = rememberUpdatedState(context)

    NotificationPermissionHandler(
        viewModel = permissionViewModel,
        onGranted = {
            val currentSong = latestSong.value
            val isPlaying = latestIsPlaying.value
            val ctx = latestContext.value

            if (isPlaying) {
                songPlayerViewModel.pauseSong(ctx)
            } else {
                currentSong?.let { songPlayerViewModel.playSong(ctx, it) }
            }
        },
        onDenied = {
            Toast.makeText(context, "Quyền hiển thị thông báo bị từ chối", Toast.LENGTH_SHORT).show()
        }
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            val title = song?.title ?: "Chi tiết bài hát"
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        title,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Up button"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // lay bai hat
            AsyncImage(
                modifier = Modifier
                    .size(250.dp),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(song?.imageUrl)
                    .placeholderMemoryCacheKey("image_${song?.id}")
                    .memoryCacheKey("image_${song?.id}")
                    .build(),
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(8.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(8.dp))
            Text(modifier = Modifier.padding(top = 8.dp), text = song?.title ?: "")
            Text(modifier = Modifier.padding(vertical = 4.dp), text = song?.artist ?: "")

            HorizontalDivider()
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // prev
                IconButton(modifier = Modifier.padding(end = 8.dp), onClick = { }) {
                    Icon(
                        modifier = Modifier.size(48.dp),
                        imageVector = Icons.Default.SkipPrevious,
                        contentDescription = "Skip to previous song"
                    )
                }

                // play/pause
                IconButton(
                    modifier = Modifier.size(64.dp),
                    onClick = {
                        val rationale = if (Build.VERSION.SDK_INT >= 33) {
                            ActivityCompat.shouldShowRequestPermissionRationale(
                                activity,
                                permission
                            )
                        } else false

                        permissionViewModel.onEnableClick(rationale)
                    }
                ) {
                    Icon(
                        modifier = Modifier.size(64.dp),
                        imageVector = if (uiState.isPlaying) Icons.Default.PauseCircle else Icons.Default.PlayCircle,
                        contentDescription = "Skip to previous song"
                    )
                }

                // next
                IconButton(modifier = Modifier.padding(start = 8.dp), onClick = { }) {
                    Icon(
                        modifier = Modifier.size(48.dp),
                        imageVector = Icons.Default.SkipNext,
                        contentDescription = "Skip to previous song"
                    )
                }
            }
        }
    }
}