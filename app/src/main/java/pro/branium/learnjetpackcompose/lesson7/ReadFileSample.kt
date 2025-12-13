package pro.branium.learnjetpackcompose.lesson7

import java.io.File
import java.io.BufferedReader

fun readFileWithBuffer(filename: String) {
    val file = File(filename)

    // 'use' đảm bảo BufferedReader sẽ được đóng sau khi sử dụng
    file.bufferedReader().use { reader: BufferedReader ->
        var line: String?
        while (reader.readLine().also { line = it } != null) {
            println(line)
        }
    }
}

fun main() {
    val filename = "example.txt"
    readFileWithBuffer(filename)
}
