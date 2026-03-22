package pro.branium.learnjetpackcompose.lesson20.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pro.branium.learnjetpackcompose.lesson20.data.repository.SongRepositoryImpl
import pro.branium.learnjetpackcompose.lesson20.domain.repository.SongRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class SongRepositoryModule {
    @Binds
    abstract fun bindSongRepository(
        impl: SongRepositoryImpl
    ): SongRepository
}