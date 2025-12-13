package pro.branium.learnjetpackcompose.oopbasic

fun main() {
    val studentA = Student(
        "SV1001",
        "Le Cong Tuan Anh",
        "tuananh@xmail.com",
        3.25f,
        "CNTT",
        2
    )

    studentA.fullName // ok
    studentA.email // ok
    studentA.gpa // ok
    studentA.major // ok
    studentA.year // ok

    studentA.doExam("OOP")
    studentA.doQuiz("OOP")
    studentA.doAttendance()
    studentA.payFee(100000.0)


    val studentB = Student(
        studentId = "Le Cong Tuan Anh",
        fullName = "tuananh@xmail.com",
    )

    val studentC = Student(fullName = "Le Cong Tuan Anh")

    val studentD = Student()

    println(studentA)
    println(studentB)
    println(studentC)

    println(studentC == studentA)
}

// private: sử dụng nội bộ bên trong class chứa nó
// public: sử dụng cả trong và bên ngoài class chứa nó
