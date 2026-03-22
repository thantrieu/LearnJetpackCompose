package pro.branium.learnjetpackcompose.lesson14

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerFormatter
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.DateRangePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TimeInput
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import java.util.Calendar
import java.util.Locale

// chọn ngày tháng: Date picker
// chọn giờ phút: Time picker

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerSample() {
    var shouldShowTimePicker by remember { mutableStateOf(false) }
    val current = Calendar.getInstance()

    var timePickerState = rememberTimePickerState(
        initialHour = current.get(Calendar.HOUR_OF_DAY),
        initialMinute = current.get(Calendar.MINUTE),
        is24Hour = true
    )
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = {
            shouldShowTimePicker = !shouldShowTimePicker
        }) {
            Text(text = "Select time")
        }

        if (shouldShowTimePicker) {
            TimeInput(state = timePickerState)
        }
    }
}

@Composable
fun DatePickerSample() {
    var shouldShowDatePicker by remember { mutableStateOf(false) }
    val state = rememberDatePickerState()
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = {
            shouldShowDatePicker = !shouldShowDatePicker
        }) {
            Text(text = "Select time")
        }
        if (shouldShowDatePicker) {
            DatePicker(state = state)
        }

        try {
            // todo 01-01-2025 => 2025/01/06
        } catch (_: Exception) {

        }
    }
}