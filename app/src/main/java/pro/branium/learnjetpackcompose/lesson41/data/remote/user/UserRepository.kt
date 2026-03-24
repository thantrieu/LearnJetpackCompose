package pro.branium.learnjetpackcompose.lesson41.data.remote.user

import pro.branium.learnjetpackcompose.lesson41.data.remote.BaseResponse
import pro.branium.learnjetpackcompose.lesson41.data.remote.ChatApiService
import pro.branium.learnjetpackcompose.lesson41.data.remote.chat.ChatRetrofitClient
import pro.branium.learnjetpackcompose.lesson41.domain.model.User

class UserRepository(
    private val api: ChatApiService = ChatRetrofitClient.api
) {
    suspend fun saveUserInfo(user: User): Result<BaseResponse> {
        return try {
            val response = api.saveUserInfo(user)
            if (response.success) {
                Result.success(response)
            } else {
                Result.failure(Exception(response.errorMessage))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}