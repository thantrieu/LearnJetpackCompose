package pro.branium.learnjetpackcompose.lesson20.utils

import java.io.File
import java.io.IOException

fun readFileSafely(path: String): Result<String> {
    return try {
        Result.success(File(path).readText(Charsets.UTF_8))
    } catch (e: IOException) {
        Result.failure(e)
    }
}

fun main() {
    val filePath = "input.txt"
    readFileSafely(filePath).onSuccess {
        println(it)
    }.onFailure {
        println("Read file failed: ${it.message}")
    }
}