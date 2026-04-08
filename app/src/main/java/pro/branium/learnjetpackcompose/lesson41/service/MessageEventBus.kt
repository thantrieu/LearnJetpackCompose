package pro.branium.learnjetpackcompose.lesson41.service

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import pro.branium.learnjetpackcompose.lesson41.domain.model.Message

object MessageEventBus {
    private val _newMessage = MutableSharedFlow<Message>()
    val newMessage: SharedFlow<Message> = _newMessage.asSharedFlow()

    suspend fun emitNewMessage(message: Message) {
        _newMessage.emit(message)
    }
}
