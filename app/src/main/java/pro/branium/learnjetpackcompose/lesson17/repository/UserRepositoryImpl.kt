package pro.branium.learnjetpackcompose.lesson17.repository

import kotlinx.coroutines.flow.Flow
import pro.branium.learnjetpackcompose.lesson17.db.UserDao
import pro.branium.learnjetpackcompose.lesson17.db.UserEntity
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : UserRepository {
    override suspend fun insertUser(user: UserEntity) {
        userDao.insertUser(user)
    }

    override suspend fun updateUser(user: UserEntity) {
        userDao.updateUser(user)
    }

    override suspend fun deleteUser(user: UserEntity) {
        userDao.deleteUser(user)
    }

    override suspend fun clearALL() {
        userDao.clearALL()
    }

    override fun getAllUsers(): Flow<List<UserEntity>> {
        return userDao.getAllUsers()
    }

    override fun getUserById(userId: Int): UserEntity? {
        return userDao.getUserById(userId)
    }

    override fun getUserByEmail(fullName: String): List<UserEntity> {
        return userDao.getUserByEmail(fullName)
    }
}