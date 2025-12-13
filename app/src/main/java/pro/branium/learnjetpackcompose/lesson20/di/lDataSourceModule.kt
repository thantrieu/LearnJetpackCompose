package pro.branium.learnjetpackcompose.lesson20.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pro.branium.learnjetpackcompose.lesson20.data.local.datasource.SongLocalDataSource
import pro.branium.learnjetpackcompose.lesson20.data.local.datasource.SongLocalDataSourceImpl
import pro.branium.learnjetpackcompose.lesson20.data.remote.datasource.SongRemoteDataSource
import pro.branium.learnjetpackcompose.lesson20.data.remote.datasource.SongRemoteDataSourceImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class lDataSourceModule {
    @Binds
    abstract fun bindSongLocalDataSource(
        impl: SongLocalDataSourceImpl
    ): SongLocalDataSource

    @Binds
    abstract fun bindSongRemoteDataSource(
        impl: SongRemoteDataSourceImpl
    ): SongRemoteDataSource
}