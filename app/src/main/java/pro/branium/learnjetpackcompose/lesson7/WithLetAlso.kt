package pro.branium.learnjetpackcompose.lesson7

class Student {
    var name: String = ""
    var rollNo: Int = 0
    var gpa: Float = 0f
    var major: String = ""

    fun displayDetails() {
        println("Name: $name")
        println("Roll No: $rollNo")
        println("GPA: $gpa")
    }

    fun doExam() {
        println("Exam done")
    }

    fun payFee(amount: Float) {
        println("Fee paid: $amount")
    }
}

fun main() {
    val student = Student().apply { //dùng khi muốn update/thực hiện lời gọi nhằm cập nhật chính nó
        name = "Le Hoai Nam"
        gpa = 3.25f
        rollNo = 1
        major = "Computer Science"

        doExam()
    }

    val student2 = Student()

    student2.run {
        this.name = "Le Hoai Nam"
        this.gpa = 3.25f
        this.rollNo = 1
        this.major = "Computer Science"

        doExam()
    }

    student.displayDetails()

    with(student2) {
        this.gpa = 3.2666f
        student2.displayDetails()
        student2.payFee(25000f)
    }
}

// let: thường dùng để kiểm tra khác null
// apply: cập nhật bản thân đôi tượng mới
// run: thực hiện kích hoạt chương trình bất đồng bộ
// also: cập nhật sau một thời gian sử dụng
// with: