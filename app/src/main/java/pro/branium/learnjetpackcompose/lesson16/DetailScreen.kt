package pro.branium.learnjetpackcompose.lesson16

import androidx.activity.ComponentActivity
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import pro.branium.learnjetpackcompose.lesson13.SongViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun SongDetailScreen(
    songId: String?,
    navController: NavController,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope
) { // màn hình chi tiết
    val songViewModel: SongViewModel =
        viewModel(LocalContext.current as ComponentActivity)
    val song by songViewModel.selectedSong.collectAsState()
    songViewModel.getSongById(songId = songId)

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
            with(sharedTransitionScope) {
                AsyncImage(
                    modifier = Modifier
                        .size(250.dp)
                        .sharedBounds(
                            rememberSharedContentState(
                                key = "image_${song?.id}"
                            ),
                            animatedVisibilityScope = animatedContentScope
                        )
                        /*
                        .sharedElement(
                            sharedTransitionScope.rememberSharedContentState(
                                key = "image_${song?.id}"
                            ),
                            animatedVisibilityScope = animatedContentScope
                        )*/,
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(song?.image)
                        .placeholderMemoryCacheKey("image_${song?.id}")
                        .memoryCacheKey("image_${song?.id}")
                        .build(),
                    contentDescription = null
                )
                HorizontalDivider()
                Text(text = song?.title ?: "")
                Text(text = song?.artist ?: "")
            }
        }
    }
}