package pro.branium.learnjetpackcompose.lesson20.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pro.branium.learnjetpackcompose.lesson20.data.local.datasource.ThemeLocalDataSource
import pro.branium.learnjetpackcompose.lesson20.data.local.datasource.ThemeLocalDataSourceImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class ThemeLocalDataSourceModule {
    @Binds
    abstract fun bindThemeLocalDataSource(
        impl: ThemeLocalDataSourceImpl
    ): ThemeLocalDataSource
}