package pro.branium.learnjetpackcompose.ui.theme

fun main() {
    val numers = listOf(8, 5, 41, 23, 6, 54, 7, 5, 6, 45, 0, 8, 5, 5, 22)
    println("Truoc khi sap xep: $numers")
    val sortedNumbers = numers.sorted()
    val reversedNumbers = sortedNumbers.reversed()
    println("Sau khi sap xep tang dan: $sortedNumbers")
    println("Sau khi sap xep giam dan: $reversedNumbers")
}
