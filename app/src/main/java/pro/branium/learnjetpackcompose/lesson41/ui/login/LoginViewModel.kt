package pro.branium.learnjetpackcompose.lesson41.ui.login

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import pro.branium.learnjetpackcompose.lesson41.data.remote.user.UserRepository
import pro.branium.learnjetpackcompose.lesson41.domain.model.User
import javax.inject.Inject

class LoginViewModel(
    private val repository: UserRepository
) : ViewModel() {
    private val _loggedInUser = MutableStateFlow<User?>(null)
    val loggedInUser = _loggedInUser.asStateFlow()

    init {
        getUserLocally()
    }

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

    fun saveUserLocally(user: User) {
        viewModelScope.launch {
            repository.saveUserLocally(user)
        }
    }

    private fun getUserLocally() {
        val result = repository.getUserLocally()
        if (result != null) {
            _loggedInUser.value = result
        }
    }

    class LoginViewModelFactory @Inject constructor(
        private val repository: UserRepository
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
                return LoginViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}