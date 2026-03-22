package pro.branium.learnjetpackcompose.lesson41.data.remote

import retrofit2.http.Body
import retrofit2.http.POST

interface ChatApiService {

    @POST("sendMessageRest")
    suspend fun sendMessage(
        @Body request: MessageRequest
    ): MessageResponse
}