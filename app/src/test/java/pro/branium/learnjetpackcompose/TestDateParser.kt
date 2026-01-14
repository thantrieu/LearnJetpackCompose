package pro.branium.learnjetpackcompose

import junit.framework.TestCase.assertEquals
import org.junit.Test
import pro.branium.learnjetpackcompose.lesson20.utils.dateToString
import java.util.Date

class TestDateParser {
    @Test
    fun givenCorrectDate_whenParseDate_thenReturnCorrectString() {
        val date = Date()
        val expected = "13/01/2026"
        val actual = dateToString(date)
        assertEquals(expected, actual)
    }

    // quy tắc viết testcase:
    /**
     * 1. mỗi một test == 1 hàm
     * 2. test các trường hợp biên.
     */
}