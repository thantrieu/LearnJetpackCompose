package pro.branium.learnjetpackcompose.oopbasic

class Student(
    val studentId: String = "",
    val fullName: String = "",
    val email: String = "",
    val gpa: Float = 0f,
    val major: String = "",
    val year: Int = 1
) {

    private fun doHomeWork() {
        println("Student ID: $studentId Doing homework for $fullName")
    }

    fun doExam(subject: String) {
        println("Doing exam for $fullName in $subject")
    }

    fun doQuiz(subject: String) {
        println("Doing quiz for $fullName in $subject")
    }

    fun doAttendance() {
        println("Doing attendance for $fullName")
    }

    fun payFee(amount: Double) {
        println("Paying $amount to $fullName")
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Student

        return studentId == other.studentId
    }

    override fun hashCode(): Int {
        return studentId.hashCode()
    }

    override fun toString(): String {
        return "Student(studentId='$studentId', fullName='$fullName', " +
                "email='$email', gpa=$gpa, major='$major', year=$year)"
    }

}

// fun: function == hàm