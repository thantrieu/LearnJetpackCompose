package pro.branium.learnjetpackcompose.lesson13

import android.media.Image
import android.widget.Space
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.HideSource
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.Report
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetSample() {
    val songViewModel: SongViewModel = viewModel()
    val songs by songViewModel.songs.collectAsState()
    val isLoading by songViewModel.isLoading.collectAsState()

    Scaffold(
        snackbarHost = {},
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // TH 1: Loading -> show indicator loading
            // TH2: loading success -> hide loading indicator and show song list

            if (isLoading) {
                CircularProgressIndicator()
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(
                        count = songs.songs.size,
                        key = { index -> songs.songs[index].id }
                    ) { index ->
                        val song = songs.songs[index]
                        SongItem(song)
                        if (index < songs.songs.size - 1) {
                            HorizontalDivider()
                        }
                    }
                }
            }

//            Button(onClick = { shouldShowBottomSheet = true }) {
//                Text(text = "Show Bottom Sheet")
//            }
//
//            if (shouldShowBottomSheet) {
//                ModalBottomSheet(
//                    modifier = Modifier.fillMaxSize(),
//                    onDismissRequest = {
//                        shouldShowBottomSheet = false
//                    },
//                    sheetState = sheetState
//                ) {
//                    BottomSheetContent { menuItemName ->
//                        Toast.makeText(context, menuItemName, Toast.LENGTH_SHORT).show()
//                        sheetScope.launch {
//                            sheetState.hide()
//                        }.invokeOnCompletion {
//                            if (!sheetState.isVisible) {
//                                shouldShowBottomSheet = false
//                            }
//                        }
//                    }
//                }
//            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SongItem(song: SongDto) {
    var showBottomSheet by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .clickable {
            }) {
        AsyncImage(
            modifier = Modifier
                .size(64.dp)
                .clip(shape = RoundedCornerShape(16.dp)),
            model = song.image,
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(maxLines = 1, text = song.title, overflow = TextOverflow.Ellipsis)
            Spacer(modifier = Modifier.height(6.dp))
            Text(text = song.artist)
        }
        Spacer(modifier = Modifier.width(8.dp))
        IconButton(
            onClick = {
                showBottomSheet = true
            },
            modifier = Modifier.align(Alignment.CenterVertically)
        ) {
            Icon(imageVector = Icons.Default.MoreHoriz, contentDescription = null)
        }
    }
    if (showBottomSheet) {
        SongBottomSheet({ showBottomSheet = false }, song.title)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SongBottomSheet(onDismiss: () -> Unit, songTitle: String) {
    val context = LocalContext.current
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)
    val sheetScope = rememberCoroutineScope()
    ModalBottomSheet(
        modifier = Modifier.fillMaxSize(),
        onDismissRequest = onDismiss,
        sheetState = sheetState
    ) {
        BottomSheetContent { menuItemName ->
            Toast.makeText(context, songTitle, Toast.LENGTH_SHORT).show()
            sheetScope.launch {
                sheetState.hide()
            }
        }
    }
}

data class BottomSheetItem(
    val id: Int,
    val icon: ImageVector,
    val text: String,
    val onClick: (String) -> Unit
)

@Composable
fun BottomSheetContent(onclick: (String) -> Unit) {
    val action: (String) -> Unit = {}

    val menuItems = listOf(
        BottomSheetItem(1, Icons.Default.Settings, "Settings", action),
        BottomSheetItem(2, Icons.Default.Share, "Share", action),
        BottomSheetItem(3, Icons.Default.Edit, "Edit", action),
        BottomSheetItem(4, Icons.Default.Report, "Report", action),
        BottomSheetItem(5, Icons.Default.Delete, "Delete", action),
        BottomSheetItem(6, Icons.Default.ContentCopy, "Copy", action),
        BottomSheetItem(7, Icons.Default.Cancel, "Cancel", action),
        BottomSheetItem(8, Icons.Default.HideSource, "Hide", action),
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Text(
            modifier = Modifier.padding(bottom = 16.dp),
            text = "Option Menus", style = MaterialTheme.typography.titleMedium
        )

        HorizontalDivider(modifier = Modifier.padding(bottom = 12.dp))

        menuItems.forEach { menuItem ->
            MenuItem(icon = menuItem.icon, text = menuItem.text) {
                onclick(menuItem.text)
            }
        }
    }
}

/*

Icon   text description
 */
@Composable
fun MenuItem(icon: ImageVector, text: String, onclick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
            .clickable {
                onclick()
            }) {
        Icon(
            modifier = Modifier.size(24.dp),
            imageVector = icon,
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = text)
    }
}

@Preview
@Composable
fun BottomSheetSamplePreview() {
    BottomSheetSample()
}