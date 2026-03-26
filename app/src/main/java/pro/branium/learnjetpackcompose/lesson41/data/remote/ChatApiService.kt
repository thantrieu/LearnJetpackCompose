package pro.branium.learnjetpackcompose.lesson41.data.remote

import pro.branium.learnjetpackcompose.lesson41.data.remote.chat.MessageRequest
import pro.branium.learnjetpackcompose.lesson41.data.remote.chat.MessageResponse
import pro.branium.learnjetpackcompose.lesson41.data.remote.chat.RecentMessageRequest
import pro.branium.learnjetpackcompose.lesson41.data.remote.chat.RecentMessageResponse
import pro.branium.learnjetpackcompose.lesson41.domain.model.User
import retrofit2.http.Body
import retrofit2.http.POST

interface ChatApiService {
    @POST("saveUserInfo")
    suspend fun saveUserInfo(@Body user: User): BaseResponse

    @POST("sendMessageRest")
    suspend fun sendMessage(
        @Body request: MessageRequest
    ): MessageResponse

    @POST("getRecentMessages")
    suspend fun getMessages(@Body body: RecentMessageRequest): RecentMessageResponse
}