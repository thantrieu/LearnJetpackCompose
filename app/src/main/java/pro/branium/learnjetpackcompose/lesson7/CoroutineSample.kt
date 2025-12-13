package pro.branium.learnjetpackcompose.lesson7

import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        launch {
            printNumber(10)
        }
        launch {
            printRandomNumber(10)
        }
    }
}

suspend fun printNumber(number: Int) {
    for (i in 1..number) {
        println("$i ")
        if (i % 10 == 0) {
            println()
        }
        delay(1500)
    }
}

suspend fun printRandomNumber(limit: Int) {
    var counter = 0
    while (counter < limit) {
        val number = (0..9999999).random()
        println("random number: $number")
        counter++
        delay(1000)
    }
}