package pro.branium.learnjetpackcompose.lesson20.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pro.branium.learnjetpackcompose.lesson20.data.repository.PlaybackStateRepositoryImpl
import pro.branium.learnjetpackcompose.lesson20.domain.repository.PlaybackStateRepository


@Module
@InstallIn(SingletonComponent::class)
abstract class PLaybackStateRepositoryModule {
    @Binds
    abstract fun bindPlaybackStateRepository(
        impl: PlaybackStateRepositoryImpl
    ): PlaybackStateRepository
}