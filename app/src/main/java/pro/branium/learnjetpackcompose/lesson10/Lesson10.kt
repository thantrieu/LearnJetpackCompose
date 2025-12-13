package pro.branium.learnjetpackcompose.lesson10

fun main() {
    val someObject = SomeClass { message: String ->
        // do something
    }

    val obj2 = SomeClass (onClick = { message: String ->
        // do something
    })
}

class SomeClass(onClick: (String) -> Unit) {

}