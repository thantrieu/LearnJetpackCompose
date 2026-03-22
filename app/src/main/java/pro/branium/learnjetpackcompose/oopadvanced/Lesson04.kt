package pro.branium.learnjetpackcompose.oopadvanced
//
//import android.R
//import androidx.compose.ui.text.font.FontWeight
//
//class Lesson04 {
//    /*
//        Kế thừa, trừu tượng, đa hình, interface.
//        Nguyên lý SOLID.
//        Các extension: các hàm mở
//     */
//}
//
//open class Animal(
//    private var _name: String = "",
//    var age: Int = 0,
//    var weight: Float = 0f
//) {
//    val name: String
//        get() = _name
//}
//
//class Dog(
//    var color: String, // màu lông
//    val eyeColor: String,
//    val breed: String, // giống,
//    val sound: String
//) : Animal() {
//    private val x: String = 100.toString()
//    val y: String = 100.toString()
//
//    init {
//        age = 2
//        weight = 10.5f
//        initObjectData()
//    }
//
//    fun initObjectData() {
//        // gán dữ liệu đọc từ bàn phím, ...
//        age = 2
//        weight = 10.5f
//        fun1()
//        fun2()
//    }
//
//    private fun fun1() {
//
//    }
//
//    private fun fun2() {
//
//    }
//
//    // ...
//
//    fun bark() { // sủa
//        println("$name is barking")
//
//        name
//    }
//
//    companion object {
//        // chứa hằng số,
//        // các hàm tiện ích chung của tất cả object tạo ra từ lớp
//
//        fun getNewDog(color: String, eyeColor: String): Dog {
//            return Dog("black", "blue", "Husky", "go go")
//        }
//    }
//}
//
//val myDog = Dog.getNewDog("black", "blue")
//
//class Cat(
//    name: String, age: Int, weight: Float,
//    val color: String,
//    val canCatchMouses: String,
//    val breed: String,
//    val sound: String
//) : Animal(name, age, weight)
//
//fun main() {
//    var animal: Animal = Dog("black", "blue", "Husky", "go go")
//    val dog2: Dog = Dog(color = "black", "blue",  breed = "Husky", sound = "go go")
//
//    val newDog = animal as? Dog
//
//    animal = Cat("Tom", 2, 10.5f, "black", "yes", "Siamese", "meow meow")
//
//    newDog?.bark() // nếu newDog không null thì truy cập vào phương thức của newDog
//
//    newDog?.bark()
//
//    newDog?.bark()
//
//    println(newDog?.age)
//    println(newDog?.weight)
//    println(newDog?.color)
//    println(newDog?.eyeColor)
//    println(newDog?.breed)
//    println(newDog?.sound)
//    newDog?.bark()
//
//    // ép kiểu: đưa biến kiểu A -> B, A thường là kiểu cha của B
//}
//
//open class Father {
//    fun doSomething() {
//
//    }
//}
//
//class Child(
//    private val name: String,
//    private val age: Int,
//    // ...
//) : Father() {
//    override fun doSomething() {
//        super.doSomething()
//    }
//}
//
