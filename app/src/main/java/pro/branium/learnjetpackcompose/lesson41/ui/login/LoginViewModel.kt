package pro.branium.learnjetpackcompose.lesson41.ui.login

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pro.branium.learnjetpackcompose.lesson41.data.remote.user.UserRepository
import pro.branium.learnjetpackcompose.lesson41.domain.model.User

class LoginViewModel(
    private val repository: UserRepository = UserRepository()
) : ViewModel() {
    fun saveUserInfo(context: Context, user: User) {
        Log.e("==>", "User info: ${user.userId} - ${user.fcmToken}")
        viewModelScope.launch {
            val result = repository.saveUserInfo(user)
            if (result.isSuccess) {
                Toast.makeText(
                    context,
                    "Lưu thông tin người dùng thành công",
                    Toast.LENGTH_SHORT
                )
                    .show()
            } else {
                Toast.makeText(
                    context,
                    result.exceptionOrNull()?.message,
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        }
    }
}