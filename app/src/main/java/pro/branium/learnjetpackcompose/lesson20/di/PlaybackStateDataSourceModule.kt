package pro.branium.learnjetpackcompose.lesson20.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pro.branium.learnjetpackcompose.lesson20.data.local.datasource.PlaybackStateDataSource
import pro.branium.learnjetpackcompose.lesson20.data.local.datasource.PlaybackStateDataSourceImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class PlaybackStateDataSourceModule {
    @Binds
    abstract fun bindPlaybackStateDataSource(impl: PlaybackStateDataSourceImpl): PlaybackStateDataSource
}