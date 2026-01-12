package pro.branium.learnjetpackcompose.lesson20.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun dateToString(date: Date?): String {
    if(date == null) return ""
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return dateFormat.format(date)
}