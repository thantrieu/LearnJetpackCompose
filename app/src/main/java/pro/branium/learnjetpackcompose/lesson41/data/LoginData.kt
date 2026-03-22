package pro.branium.learnjetpackcompose.lesson41.data

data class UserRequest(
    val idToken: String
)

data class ApiResponse(
    val status: String,
    val message: String,
    val token: String?
)