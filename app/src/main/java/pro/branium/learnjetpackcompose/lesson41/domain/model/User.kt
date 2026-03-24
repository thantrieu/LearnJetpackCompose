package pro.branium.learnjetpackcompose.lesson41.domain.model

data class User(
    val userId: String = "108694973833202095556",
    val fullName: String,
    val email: String,
    val avatarUrl: String? = null,
    val fcmToken: String? = null,
)
