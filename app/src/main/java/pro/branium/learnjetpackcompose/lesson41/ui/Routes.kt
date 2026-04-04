package pro.branium.learnjetpackcompose.lesson41.ui

object Routes {
    const val LOGIN = "login"
    const val FRIENDS = "friends"
    const val CHAT = "chat/{receiverId}"

    fun chat(receiverId: String) = "chat/$receiverId"
}