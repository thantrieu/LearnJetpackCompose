package pro.branium.learnjetpackcompose.lesson20.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pro.branium.learnjetpackcompose.lesson20.data.NotificationPermissionChecker
import pro.branium.learnjetpackcompose.lesson20.data.PermissionAskedStore
import pro.branium.learnjetpackcompose.lesson20.data.local.permission.NotificationPermissionCheckerImpl
import pro.branium.learnjetpackcompose.lesson20.data.local.permission.PermissionAskedStoreImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class PermissionModule {

    @Binds
    @Singleton
    abstract fun bindPermissionAskedStore(
        impl: PermissionAskedStoreImpl
    ): PermissionAskedStore

    @Binds
    @Singleton
    abstract fun bindNotificationPermissionChecker(
        impl: NotificationPermissionCheckerImpl
    ): NotificationPermissionChecker
}
