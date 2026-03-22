package pro.branium.learnjetpackcompose.lesson17.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import pro.branium.learnjetpackcompose.lesson17.db.UserDao
import pro.branium.learnjetpackcompose.lesson17.db.UserEntity

@Database(
    entities = [
        UserEntity::class,
    ],
    version = 1,
    exportSchema = true
)
abstract class MyDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        const val DATABASE_NAME = "my_database.db"

        fun getInstance(context: Context): MyDatabase {
            return Room.databaseBuilder(
                context,
                MyDatabase::class.java,
                DATABASE_NAME
            ).build()
        }
    }
}