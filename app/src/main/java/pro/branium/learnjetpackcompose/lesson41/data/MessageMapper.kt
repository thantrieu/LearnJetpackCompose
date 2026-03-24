package pro.branium.learnjetpackcompose.lesson41.data

import pro.branium.learnjetpackcompose.lesson41.data.remote.chat.MessageRequest
import pro.branium.learnjetpackcompose.lesson41.domain.model.Message

fun Message.toMessageRequest(): MessageRequest {
    return MessageRequest(
        senderId = senderId,
        receiverId = receiverId,
        text = text,
    )
}