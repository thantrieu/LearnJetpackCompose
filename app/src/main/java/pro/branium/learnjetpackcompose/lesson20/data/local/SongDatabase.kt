package pro.branium.learnjetpackcompose.lesson20.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [SongEntity::class],
    version = 1,
    exportSchema = true
)
abstract class SongDatabase : RoomDatabase() {
    abstract fun songDao(): SongDao
}