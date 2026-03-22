package pro.branium.learnjetpackcompose.lesson7

open class Father

class X : Father() {

}

class Y : Father() {}

fun main() {
    val father: Father = X()
    val y = Y()

    try {
        //..
        // ..
        // ...
        y as X
    } catch (_: ClassCastException) {
        // ...
        // ...
        null
    }
}

// ngoại lệ chính xác, cụ thể -> ngoại lệ cha -> Exception