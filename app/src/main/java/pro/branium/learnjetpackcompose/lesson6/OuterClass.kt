package pro.branium.learnjetpackcompose.lesson6

class OuterClass {
    private lateinit var fullName: String
    private val gpa: Float = 0f
    private val address: String = ""

    inner class Inner {
        fun doSomething(newFullName: String) {
            getArea(12f)
        }
    }

    companion object { // static methods and variables
        const val PI = 3.14159f

        fun getArea(radius: Float): Float {
            return PI * radius * radius
        }
    }
}
// sử dụng khi: thường dùng trong các adapter -> đổ dữ liệu lên Recyclerview, ListView...

sealed class X {
    class Y : X()
    class XX : X()
    class Z : X()
}

sealed class NetworkRequestResult {
    class Error : NetworkRequestResult()
    class Success : NetworkRequestResult()
    class Loading : NetworkRequestResult()
    class Empty : NetworkRequestResult()
}

val requestResult = NetworkRequestResult.Success()