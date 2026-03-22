package pro.branium.learnjetpackcompose.lesson15

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun FatherFunction() {

    StateManagermentSample()
}

@Composable
fun StateManagermentSample() {
    val viewModel: MyViewModel = viewModel()

    var counter = viewModel.counter.collectAsState()
    val onClick: () -> Unit = { viewModel.updateCounter(counter.value + 1) }

    Counter(counter.value, onClick)
}

@Composable
fun Counter(counter: Int, onClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { onClick() }) {
            Text("Count: $counter")
        }
    }
}