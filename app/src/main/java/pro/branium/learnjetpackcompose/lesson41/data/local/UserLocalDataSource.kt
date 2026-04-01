package pro.branium.learnjetpackcompose.lesson41.data.local

import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.edit
import pro.branium.learnjetpackcompose.lesson41.domain.model.User
import javax.inject.Inject

class UserLocalDataSource @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {
    fun saveUserLocally(user: User) {
        sharedPreferences.edit {
            putString("userId", user.userId)
            putString("email", user.email)
            putString("name", user.fullName)
            putString("avatar", user.avatarUrl)
        }
    }

    fun getUserLocally(): User? {
        val userId = sharedPreferences.getString("userId", null) ?: ""
        if(userId.isEmpty()) return null

        val email = sharedPreferences.getString("email", null) ?: ""
        val fullName = sharedPreferences.getString("name", null) ?: ""
        val avatar = sharedPreferences.getString("avatar", null)
        Log.e("==>", "avatar: $avatar")

        return User(
            userId = userId,
            email = email,
            fullName = fullName,
            avatarUrl = avatar
        )
    }
}