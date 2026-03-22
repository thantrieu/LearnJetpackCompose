package pro.branium.learnjetpackcompose.lesson20.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun dateToString(date: Date?): String {
    if (date == null) return ""
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return dateFormat.format(date)
}

fun divide(a: Int, b: Int): Float {
    if (b != 0) return a * 1.0f / b
    else if (a == 0) {
        return Float.NEGATIVE_INFINITY
    } else {
        throw Exception("Lỗi phép chia cho 0")
    }
}

fun add(a: Int, b: Int) = a + b
