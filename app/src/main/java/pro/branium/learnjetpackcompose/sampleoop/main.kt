package pro.branium.learnjetpackcompose.sampleoop

// tạo chương trình quản lý nhân viên: bán thời gian, fulltime, quản lý, giám đốc
// nhân viên: mã nhân viên, có họ và tên, số điện thoại, lương, số ngày làm việc thực tế trong tháng.
// hành động: checkin, checkout, tính lương, làm việc...

fun main() {
    while (true) {
        // hiển thị menu tùy chọn
        println("Xin mời chọn:")
        println("1. Thêm mới nhân viên")
        println("2. Hiển thị danh sách nhân viên")
        println("3. Tìm kiếm nhân viên")
        // ....
        println("0. Thoát")
        print("Chọn: ")
        val choice = readln()
        when (choice) {
            "0" -> {
                println("Kết thúc chương trình")
                break
            }

            "1" -> {
                println("Thêm mới nhân viên")
                // gọi hàm thêm mới
            }

            "2" -> {
                println("Hiển thị danh sách nhân viên")
                // gọi hàm hiển thị ds nv
            }

            "3" -> {
                println("Tìm kiếm nhân viên")
            }

            else -> {
                println("Lựa chọn không hợp lệ, mời chọn lại.")
            }
        }
    }
}