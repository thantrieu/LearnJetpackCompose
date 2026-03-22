package pro.branium.learnjetpackcompose.lesson17.repository

import kotlinx.coroutines.flow.Flow
import pro.branium.learnjetpackcompose.lesson17.db.UserEntity

interface UserRepository {
    suspend fun insertUser(user: UserEntity)

    suspend fun updateUser(user: UserEntity)

    suspend fun deleteUser(user: UserEntity)

    suspend fun clearALL()

    fun getAllUsers(): Flow<List<UserEntity>>

    fun getUserById(userId: Int): UserEntity?

    fun getUserByEmail(fullName: String): List<UserEntity>
}