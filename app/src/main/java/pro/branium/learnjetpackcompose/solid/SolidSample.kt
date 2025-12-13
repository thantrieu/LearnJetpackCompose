package pro.branium.learnjetpackcompose.solid

class Student {
    private val studentId: String = ""
    private val fullName: String = ""
    private val email: String = ""

    fun doHomeWork() {
        println("Student ID: $studentId Doing homework for $fullName")
    }

    fun doExam() {

    }

    fun doQuiz() {

    }
}

class StudyResult {
    private lateinit var student: Student
    private var gpa: Float = 0f
}

// môn học
class Subject {
    private lateinit var subjectName: String
    fun addNewSubject() {

    }
}

class RegisterSubject {
    private val numberOfRegisteredSubject: Int = 0

    fun registerSubject() {

    }

    fun cancel(subject: Subject) {
        // ....
    }
}
