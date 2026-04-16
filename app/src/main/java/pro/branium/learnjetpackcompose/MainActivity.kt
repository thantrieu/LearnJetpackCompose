package pro.branium.learnjetpackcompose

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.credentials.GetCredentialException
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import pro.branium.learnjetpackcompose.lesson36.WebClientID
import pro.branium.learnjetpackcompose.lesson41.data.local.UserLocalDataSource
import pro.branium.learnjetpackcompose.lesson41.data.remote.user.UserRepository
import pro.branium.learnjetpackcompose.lesson41.domain.model.User
import pro.branium.learnjetpackcompose.lesson41.ui.AppNavigation
import pro.branium.learnjetpackcompose.lesson41.ui.FriendsList
import pro.branium.learnjetpackcompose.lesson41.ui.chat.ChatViewModel
import pro.branium.learnjetpackcompose.lesson41.ui.login.LoginScreen
import pro.branium.learnjetpackcompose.lesson41.ui.login.LoginViewModel
import pro.branium.learnjetpackcompose.ui.theme.AppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val credentialManager by lazy {
        CredentialManager.create(this)
    }
    private var currentUser by mutableStateOf<User?>(null)
    private lateinit var callbackManager: CallbackManager
    private val loginViewModel: LoginViewModel by viewModels {
        val dataSource = UserLocalDataSource(getSharedPreferences("user", MODE_PRIVATE))
        val repository = UserRepository(localDataSource = dataSource)
        LoginViewModel.LoginViewModelFactory(repository)
    }

    private val chatViewModel: ChatViewModel by viewModels()

    private val _navigationEvent = MutableSharedFlow<ChatNavigation?>(1)
    val navigationEvent = _navigationEvent.asSharedFlow()

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        askNotification()
        callbackManager = CallbackManager.Factory.create()
        observeLoggedInUser()
        setContent {
            AppTheme {
                AppNavigation(
                    currentUser,
                    chatViewModel = chatViewModel,
                    navigationEvent = navigationEvent,
                    loginWithFacebook = { loginWithFacebook(this) },
                    loginWithGoogle = { signInWithGoogle() },
                )
            }
        }
        handleIntent(intent)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleIntent(intent)
    }

    private fun handleIntent(intent: Intent?) {
        intent?.extras?.let {
            if (it.getString("type") == "chat_message") {
                val senderId = it.getString("senderId")
                val receiverId = it.getString("receiverId")

                lifecycleScope.launch {
                    _navigationEvent.emit(ChatNavigation(senderId, receiverId))
                }
            }
        }
    }

    private fun observeLoggedInUser() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                loginViewModel.loggedInUser.collect { user ->
                    if (user != null) {
                        currentUser = user
                    }
                }
            }
        }
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }

    fun loginWithFacebook(activity: Activity) {
        LoginManager.getInstance().logInWithReadPermissions(
            activity,
            listOf("email", "public_profile")
        )

        LoginManager.getInstance().registerCallback(
            callbackManager,
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(result: LoginResult) {
                    val accessToken = result.accessToken
                    Log.d("FB_LOGIN", "Token: ${accessToken.token}")
                }

                override fun onCancel() {
                    Log.d("FB_LOGIN", "Cancelled")
                }

                override fun onError(error: FacebookException) {
                    Log.e("FB_LOGIN", error.message ?: "")
                }
            }
        )
    }

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    private fun signInWithGoogle() {
        val googleIdOption = GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(false)
            .setServerClientId(WebClientID.CLIENT_ID)
            .build()

        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()

        lifecycleScope.launch {
            try {
                val result = credentialManager.getCredential(
                    request = request,
                    context = this@MainActivity
                )

                handleSignIn(result)
            } catch (e: GetCredentialException) {
                Toast.makeText(
                    this@MainActivity,
                    "Bạn chưa đăng nhập tài khoản google nào trên thiết bị",
                    Toast.LENGTH_SHORT
                ).show()
                e.printStackTrace()
            }
        }

    }

    private fun handleSignIn(result: GetCredentialResponse) {
        val credential = result.credential

        if (credential is CustomCredential &&
            credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
        ) {
            val googleIdTokenCredential =
                GoogleIdTokenCredential.createFrom(credential.data)

            // ✅ Lấy info trực tiếp từ Google
            val uid = googleIdTokenCredential.uniqueId
            val fullName = googleIdTokenCredential.displayName ?: ""
            val email = googleIdTokenCredential.email ?: ""
            val avatar = googleIdTokenCredential.profilePictureUri?.toString() ?: ""

            val fcmToken = getSharedPreferences("user", MODE_PRIVATE)
                .getString("fcmToken", null)
            val userProfile = User(
                userId = uid,
                fullName = fullName,
                email = email,
                avatarUrl = avatar,
                fcmToken = fcmToken
            )
            currentUser = userProfile
            loginViewModel.saveUserLocally(userProfile)
            loginViewModel.saveUserInfo(this, userProfile)
        }
    }

    private fun logout() {
        lifecycleScope.launch {
            credentialManager.clearCredentialState(
                ClearCredentialStateRequest()
            )
        }
    }

    private fun askNotification() {
        if (Build.VERSION.SDK_INT >= 33) {
            requestPermissions(
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                100
            )
        }
    }

    data class ChatNavigation(
        val senderId: String?,
        val receiverId: String?
    )
}
