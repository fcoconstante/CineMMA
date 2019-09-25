package com.fcoconstante.cinemma.data.db

import android.content.Context
import androidx.room.*
import com.fcoconstante.cinemma.util.Converters
import com.fcoconstante.cinemma.data.db.entities.Movie

@Database(
    entities = [Movie::class],
    version = 1
)

@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getMovieDao() : MovieDao

    companion object{
        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context : Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                instance = it
            }

        }

        private fun buildDatabase(context:Context) =
            Room.databaseBuilder(context.applicationContext,
                AppDatabase::class.java,"MyDatabase.db").build()
    }
}