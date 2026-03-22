package pro.branium.learnjetpackcompose.lesson6

import kotlin.text.compareTo

fun main() {
//    val intArr = arrayOf(1, 2, 3, 4, 5)
//    val strArr = arrayOf("Hello", "World", "Hello")
//    val charArr = charArrayOf('a', 'b', 'c')
//    val longArr = longArrayOf(1L, 2L, 3L)
//    val floatArr = floatArrayOf(1.0f, 2.0f, 3.0f)
//    val boolArr = booleanArrayOf(true, false, true)
//
//    printArray(intArr)
//    printArray(strArr)
//    printArray(longArr.toTypedArray())
//    printArray(charArr.toTypedArray())
//    printArray(floatArr.toTypedArray())
//    printArray(boolArr.toTypedArray())
//
//    val myDic = Dic<String, String>("Today", "Hôm nay")
//    val myDic2 = Dic("Yesterday", "Hôm qua")
//    val myDic3 = Dic("Tomorrow", "Ngày mai")


    val s1 = Student("S001", "Hung", 3.55f)
    val s2 = Student("S002", "Huong", 3.51f)

    val studentComparator = GpaComparator()
    val result = studentComparator.compare(s1, s2)
    println(result)

}

fun <T> printArray(array: Array<T>) {
    for (element in array) {
        println(element)
    }
}

class Dic<K, V>(val key: K, val value: V)

interface Comparable<T> {
    fun compareTo(other: T): Int
}

interface Comparator<T> {
    fun compare(o1: T, o2: T): Int
}

class StudentIdComparator : Comparator<Student> {
    override fun compare(o1: Student, o2: Student): Int {
        return o1.id.compareTo(o2.id) // so sanh id
    }
}

class StudentNameComparator : Comparator<Student> {
    override fun compare(o1: Student, o2: Student): Int {
        return o1.name.compareTo(o2.name) // so sanh name
    }
}

class GpaComparator : Comparator<Student> {
    override fun compare(o1: Student, o2: Student): Int {
        return o1.gpa.compareTo(o2.gpa) // so sanh gpa
    }
}


data class Student(
    val id: String,
    val name: String,
    val gpa: Float
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Student

        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }


}

// abcd
// abcx