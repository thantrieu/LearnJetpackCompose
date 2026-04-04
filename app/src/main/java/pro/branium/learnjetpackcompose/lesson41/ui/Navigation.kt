package pro.branium.learnjetpackcompose.lesson41.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pro.branium.learnjetpackcompose.lesson41.domain.model.User
import pro.branium.learnjetpackcompose.lesson41.ui.chat.ChatScreen
import pro.branium.learnjetpackcompose.lesson41.ui.chat.ChatViewModel
import pro.branium.learnjetpackcompose.lesson41.ui.login.LoginScreen

@Composable
fun AppNavigation(
    currentUser: User?,
    chatViewModel: ChatViewModel,
    loginWithFacebook: () -> Unit,
    loginWithGoogle: () -> Unit
) {
    val navController = rememberNavController()

    // Điều hướng theo trạng thái login
    LaunchedEffect(currentUser) {
        if (currentUser == null) {
            navController.navigate(Routes.LOGIN) {
                popUpTo(0)
            }
        } else {
            navController.navigate(Routes.FRIENDS) {
                popUpTo(0)
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = Routes.LOGIN
    ) {

        // ===== LOGIN =====
        composable(Routes.LOGIN) {
            LoginScreen(
                onLoginClick = { loginWithFacebook() },
                onGoogleLoginClick = { loginWithGoogle() }
            )
        }

        // ===== FRIENDS =====
        composable(Routes.FRIENDS) {

            // Side-effect phải đặt trong LaunchedEffect
            LaunchedEffect(currentUser?.userId) {
                currentUser?.userId?.let {
                    chatViewModel.getFriends(it)
                }
            }

            FriendsList(
                chatViewModel = chatViewModel,
                onFriendClick = { receiverId ->
                    navController.navigate(Routes.chat(receiverId))
                }
            )
        }

        // ===== CHAT =====
        composable(Routes.CHAT) { backStackEntry ->
            val receiverId = backStackEntry.arguments?.getString("receiverId") ?: return@composable
            val selectedFriend = chatViewModel.getFriendById(receiverId)
            val sender = currentUser
            if (selectedFriend != null && sender != null) {
                ChatScreen(
                    sender = sender,
                    receiver = selectedFriend,
                    navController = navController,
                )
            }
        }
    }
}