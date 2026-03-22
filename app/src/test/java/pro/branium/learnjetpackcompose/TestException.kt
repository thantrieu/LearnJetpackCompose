package pro.branium.learnjetpackcompose

import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Assert.fail
import org.junit.Test
import pro.branium.learnjetpackcompose.lesson20.utils.divide

class TestException {
    /**
     * assertThrows()
     * try-catch
     *
     * fun divide(a: Int, b: Int): Float {
     *     if (b != 0) return a * 1.0f / b
     *     else if (a == 0) {
     *         return Float.NEGATIVE_INFINITY
     *     } else {
     *         throw Exception("Lỗi phép chia cho 0")
     *     }
     * }
     */

    // cach 1: assertThrows()

    @Test
    fun givenZeroDenominatorAndNonZeroNumerator_whenDivide_thenThrowException() {
        val exception = assertThrows(Exception::class.java) {
            divide(1, 0)
        }
        val expectedMessage = "Lỗi phép chia cho 0"
        val actualMessage = exception.message
        assertEquals(expectedMessage, actualMessage)
    }

    // cach 2: try-catch
    @Test
    fun testException2() {
        try {
            divide(1, 0)
            fail("Expected an exception to be thrown")
        } catch (e: Exception) {
            val expectedMessage = "Lỗi phép chia cho 0"
            val actualMessage = e.message
            assertEquals(expectedMessage, actualMessage)
        }
    }
}