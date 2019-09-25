package com.fcoconstante.cinemma.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fcoconstante.cinemma.data.db.entities.Movie

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAllMovies(movies : List<Movie>)

    @Query("SELECT * FROM Movie")
    fun getMovies() : LiveData<List<Movie>>

}