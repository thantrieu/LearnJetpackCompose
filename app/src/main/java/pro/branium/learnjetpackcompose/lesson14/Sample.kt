package pro.branium.learnjetpackcompose.lesson14

fun main( ){
    val result = add(1, 2)
}

fun add(a: Int, b: Int, c: Int = 0) : Int {
    return a + b + c
}