package pro.branium.learnjetpackcompose.lesson41.domain.model

data class User(
    val uid: String = "108694973833202095556",
    val fullName: String,
    val email: String,
    val avatarUrl: String? = null
)
