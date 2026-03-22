package pro.branium.learnjetpackcompose.lesson34

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import pro.branium.learnjetpackcompose.R

@Composable
fun FireStoreOperations() {
    val context = LocalContext.current
    val firebaseViewModel: SongViewModel = viewModel()
    val songs by firebaseViewModel.songs.collectAsState()

    val action: () -> Unit = {
        firebaseViewModel.readJsonFromFile(context = context, fileName = R.raw.songs)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = action) {
            Text("Lưu dữ liệu lên Firebase")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                firebaseViewModel.updateSongToFirestore(songId = "1073419268")
            }
        ) {
            Text("Update dữ liệu")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                firebaseViewModel.deleteSongFromFirebase(songId = "CwPnwwLmtHbHibqHJOe7")
            }
        ) {
            Text("Xóa bài hát")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                firebaseViewModel.deleteCollection("songs10")
            }
        ) {
            Text("Xóa DS bài hát")
        }
    }
}
