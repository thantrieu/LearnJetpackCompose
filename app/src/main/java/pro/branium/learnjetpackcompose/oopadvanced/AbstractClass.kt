package pro.branium.learnjetpackcompose.oopadvanced

import androidx.compose.ui.graphics.painter.BitmapPainter

// lớp trừu tượng và interface

interface Animal {
    val name: String
    fun run()

    fun eat()

    fun sleep()

}

// triển khai interface

abstract class Dog() : Animal {
    override fun run() {
        // chạy bằng 4 chân
    }

    override fun sleep() {
        TODO("Not yet implemented")
    }

}

class Bird() : Animal {
    private val _name: String = "...."

    override val name: String
        get() = _name

    override fun run() {
        // jump ...
    }

    override fun eat() {
        TODO("Not yet implemented")
    }

    override fun sleep() {
        TODO("Not yet implemented")
    }

}

abstract class Fish() : Animal {
    override fun run() {
        // cá bơi bằng vây, ở dưới nước
    }

    override fun eat() {
        TODO("Not yet implemented")
    }

    override fun sleep() {
        TODO("Not yet implemented")
    }

}

interface  A

interface B : Animal

class C() : B {
    override val name: String = ""

    override fun run() {
        TODO("Not yet implemented")
    }

    override fun eat() {
        TODO("Not yet implemented")
    }

    override fun sleep() {
        TODO("Not yet implemented")
    }

}


class MyClass {
    private val name: String = "...."

    fun doSomething() {
//        .....
    }
}

fun main() {
    val obj = MyClass()
//    obj.name
}