package pro.branium.learnjetpackcompose.extension

import android.hardware.biometrics.BiometricManager

fun main() {
    val arr = arrayOf(101, 12, 30, 4, 5)
    arr.sort()
    print(arr.contentToString())

    arr.reverse()
    print(arr.contentToString())

}
// hàm mở rộng: mục đích -> bổ sung các chức năng cho các lớp đã có trước
// 1. cần có một hàm để đếm số từ trong một chuỗi cho trước.
// 2. viết hoa chữ cái ở cuối mỗi từ
// 3. đưa chữ cái đang viết hoa -> viết thường, thường -> viết hoa
// 4. viết hàm mở rộng đưa một số nguyên ở hệ 10 thành một chuỗi ở hệ 2; 16


/**
 * Hàm đếm số từ trong một chuỗi cho trước
 * @return số từ trong chuỗi cho trước
 */
fun String.countWords(): Int {
    val words = this.split(" ")
    val numberOfWord = words.size
    return numberOfWord
}

// function2: viết hoa chữ cái ở cuối mỗi từ

fun String.capitalizeEndCharacterOfEachWord(): String {
    val words = this.split(" ")
    val result = StringBuilder()
    for (word in words) {
        val charArr = word.toCharArray()
        charArr[charArr.size - 1] = charArr[charArr.size - 1].uppercaseChar()
        result.append(String(charArr))
        result.append(" ")
    }
    return result.toString().trimEnd()
}


// hôm
// nay
// là
// cuối
// tuần

// cú pháp chung:
//fun Kiểu.tênPhươngThức(các tham số): TrảVề {
//    // triển khai
//}