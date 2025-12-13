package pro.branium.learnjetpackcompose.lesson20.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import pro.branium.learnjetpackcompose.lesson20.data.local.SongDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideSongDatabase(@ApplicationContext context: Context): SongDatabase {
        return Room.databaseBuilder(
            context,
            SongDatabase::class.java,
            "song_database.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideSongDao(songDatabase: SongDatabase) = songDatabase.songDao()
}