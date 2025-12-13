package pro.branium.learnjetpackcompose.oopadvanced

class Person1(var age: Int = 20) {
    private var _name: String = ""

    val name: String = _name // read only property

    fun setName(name: String) {
        _name = name
    }
}