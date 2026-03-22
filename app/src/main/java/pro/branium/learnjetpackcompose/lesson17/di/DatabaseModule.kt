package pro.branium.learnjetpackcompose.lesson17.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import pro.branium.learnjetpackcompose.lesson17.db.MyDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): MyDatabase {
        return MyDatabase.getInstance(context)
    }

    @Provides
    fun provideUserDao(database: MyDatabase) = database.userDao()
}