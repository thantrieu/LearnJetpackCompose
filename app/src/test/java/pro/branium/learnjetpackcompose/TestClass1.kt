package pro.branium.learnjetpackcompose

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test
import org.junit.rules.Timeout

class TestClass1 {

    @Rule
    @JvmField
    val globalTimeout: Timeout = Timeout.seconds(10)

//    @Ignore
    @Test
    fun test1() = runBlocking {
        delay(9000)
        assertTrue(true)
    }
}