package pro.branium.learnjetpackcompose.lesson17.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pro.branium.learnjetpackcompose.lesson17.repository.UserRepository
import pro.branium.learnjetpackcompose.lesson17.repository.UserRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class UserRepositoryModule {
    @Binds
    abstract fun bindUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository
}