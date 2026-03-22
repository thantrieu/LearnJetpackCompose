package pro.branium.learnjetpackcompose.lesson25

import android.app.Service
import android.content.Intent
import android.content.ServiceConnection
import android.os.Binder
import android.os.IBinder
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MyService : Service() {
    private val _numberFlow = MutableStateFlow(100)
    val numberFlow: StateFlow<Int> = _numberFlow

    private val binder = MyBinder()

    inner class MyBinder: Binder() {
        fun getService(): MyService {
            return this@MyService
        }
    }

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    fun increaseNumber() {
        _numberFlow.value++
    }

    fun decreaseNumber() {
        _numberFlow.value--
    }
}