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
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import androidx.lifecycle.lifecycleScope
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import pro.branium.learnjetpackcompose.lesson36.WebClientID
import pro.branium.learnjetpackcompose.lesson41.domain.model.User
import pro.branium.learnjetpackcompose.lesson41.ui.chat.ChatScreen
import pro.branium.learnjetpackcompose.lesson41.ui.login.LoginScreen
import pro.branium.learnjetpackcompose.ui.theme.AppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val credentialManager by lazy {
        CredentialManager.create(this)
    }
    private var currentUser by mutableStateOf<User?>(null)
    private lateinit var callbackManager: CallbackManager

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        askNotification()

        callbackManager = CallbackManager.Factory.create()

        setContent {
            AppTheme {
//                LoginScreen()
//                FireStoreOperations()
                if (currentUser == null) {
                    LoginScreen(
                        onLoginClick = {
                            loginWithFacebook(this)
                        },
                        onGoogleLoginClick = {
                            signInWithGoogle()
                        }
                    )
                } else {
                    ChatScreen(user = currentUser!!)
                }
            }
        }
//        val lifecycleOwner = this
//        setContent {
//            val themeViewModel: ThemeViewModel = hiltViewModel()
//
//            val isDarkTheme by remember(themeViewModel.isDarkMode, lifecycleOwner) {
//                themeViewModel.isDarkMode.flowWithLifecycle(lifecycleOwner.lifecycle)
//            }.collectAsState(initial = false) // initial = false hoặc isSystemInDarkTheme()
//
//            val onDarkThemeChanged: (Boolean) -> Unit = { newStatus ->
//                themeViewModel.setDarkMode(newStatus) // LƯU VÀO DATASTORE
//            }
//
//            AppTheme(darkTheme = isDarkTheme, dynamicColor = false) {
//                AppNavigation(isDarkTheme, onDarkThemeChanged)
//            }
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

            val userProfile = User(
                uid = uid,
                fullName = fullName,
                email = email,
                avatarUrl = avatar,
            )
            currentUser = userProfile
            Log.e("==>", "User: $userProfile")

            saveUserLocally(email, fullName, avatar)
        }
    }

    fun saveUserLocally(email: String?, name: String?, avatar: String?) {
        getSharedPreferences("user", MODE_PRIVATE)
            .edit()
            .putString("email", email)
            .putString("name", name)
            .putString("avatar", avatar)
            .apply()
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
}
