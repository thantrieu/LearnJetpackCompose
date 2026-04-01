package pro.branium.learnjetpackcompose.lesson41.data.remote.user

import android.content.Context.MODE_PRIVATE
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import pro.branium.learnjetpackcompose.lesson41.data.local.UserLocalDataSource
import pro.branium.learnjetpackcompose.lesson41.data.remote.BaseResponse
import pro.branium.learnjetpackcompose.lesson41.data.remote.ChatApiService
import pro.branium.learnjetpackcompose.lesson41.data.remote.chat.ChatRetrofitClient
import pro.branium.learnjetpackcompose.lesson41.domain.model.User

class UserRepository(
    private val api: ChatApiService = ChatRetrofitClient.api,
    private val localDataSource: UserLocalDataSource
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

    suspend fun saveUserLocally(user: User) {
        localDataSource.saveUserLocally(user)
    }

    fun getUserLocally(): User? {
        return localDataSource.getUserLocally()
    }
}