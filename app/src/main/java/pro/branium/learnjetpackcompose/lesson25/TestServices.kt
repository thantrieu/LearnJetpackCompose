package pro.branium.learnjetpackcompose.lesson25

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ServiceSample() {
    val viewModel: ServiceSampleViewModel = viewModel()
    // connect to service
    viewModel.bindService(LocalContext.current)
    val number by viewModel.numberFlow.collectAsState()
    // disconnect from service
//    viewModel.unbindService(LocalContext.current)

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Current value: $number")
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
        ) {
            Button(onClick = { viewModel.increaseNumber() }) {
                Text(text = "Increase")
            }
            Button(onClick = { viewModel.decreaseNumber() }) {
                Text(text = "Decrease")
            }
        }
    }
}