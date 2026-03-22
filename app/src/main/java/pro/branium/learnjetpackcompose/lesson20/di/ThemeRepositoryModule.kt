package pro.branium.learnjetpackcompose.lesson20.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pro.branium.learnjetpackcompose.lesson20.data.repository.ThemeRepositoryImpl
import pro.branium.learnjetpackcompose.lesson20.domain.repository.ThemeRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class ThemeRepositoryModule {
    @Binds
    abstract fun bindThemeRepository(impl: ThemeRepositoryImpl): ThemeRepository
}