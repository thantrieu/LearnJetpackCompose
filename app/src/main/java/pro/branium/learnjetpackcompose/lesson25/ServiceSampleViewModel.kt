package pro.branium.learnjetpackcompose.lesson25

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ServiceSampleViewModel : ViewModel() {
    private var service: MyService? = null
    private val _numberFlow = MutableStateFlow(100)
    val numberFlow: MutableStateFlow<Int> = _numberFlow

    private val connection = MyServiceConnection(
        onConnected = {
            service = it
            observeService()
        },
        onDisconnected = { service = null }
    )

    fun bindService(context: Context) {
        val intent = Intent(context, MyService::class.java)
        context.bindService(intent, connection, Context.BIND_AUTO_CREATE)
    }

    fun unbindService(context: Context) {
        try {
            context.unbindService(connection)
        } catch (_: Exception) {
        }
    }

    fun increaseNumber() {
        service?.increaseNumber()
    }

    fun decreaseNumber() {
        service?.decreaseNumber()
    }

    private fun observeService() {
        viewModelScope.launch(Dispatchers.IO) {
            service?.numberFlow?.collect {
                _numberFlow.value = it
            }
        }
    }
}

class MyServiceConnection(
    private val onConnected: (MyService) -> Unit,
    private val onDisconnected: () -> Unit
) : ServiceConnection {

    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        val binder = service as MyService.MyBinder
        onConnected(binder.getService())
    }

    override fun onServiceDisconnected(name: ComponentName?) {
        onDisconnected()
    }
}
