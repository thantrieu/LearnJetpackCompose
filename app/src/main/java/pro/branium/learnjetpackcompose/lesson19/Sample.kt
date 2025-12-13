package pro.branium.learnjetpackcompose.lesson19

data class User(
    val id: Int = 0,
    val username: String = "",
    val fullName: String = "",
    val email: String = "",
    val isMale: Boolean = false
) {
    fun builder() {
        val user = User()

    }

    fun buildId(id: Int): User {
        return User(id = id)
    }

    fun buildUsername(username: String): User {
        return User(username = username)
    }

    fun buildFullName(fullName: String): User {
        return User(fullName = fullName)
    }

    fun buildEmail(email: String): User {
        return User(email = email)
    }
}

fun main() {
    val myUser = User().buildEmail("email")
        .buildUsername("")
        .buildId(0)
        .buildEmail("gh@gmail.com")

}