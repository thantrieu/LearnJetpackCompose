package pro.branium.learnjetpackcompose.lesson20.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pro.branium.learnjetpackcompose.lesson20.data.repository.AlbumRepositoryImpl
import pro.branium.learnjetpackcompose.lesson20.domain.repository.AlbumRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class AlbumRepositoryModule {
    @Binds
    abstract fun bindAlbumRepository(
        impl: AlbumRepositoryImpl
    ): AlbumRepository
}