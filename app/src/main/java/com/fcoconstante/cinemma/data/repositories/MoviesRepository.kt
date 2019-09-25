package com.fcoconstante.cinemma.data.repositories

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fcoconstante.cinemma.data.db.AppDatabase
import com.fcoconstante.cinemma.data.db.entities.Movie
import com.fcoconstante.cinemma.data.network.DBMovieApi
import com.fcoconstante.cinemma.data.network.SafeApiRequest
import com.fcoconstante.cinemma.data.preferences.PreferenceProvider
import com.fcoconstante.cinemma.util.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

private const val MINIMUM_INTERVAL = 6

class MoviesRepository(
    private val api: DBMovieApi,
    private val db: AppDatabase,
    private val prefs: PreferenceProvider
) : SafeApiRequest() {

    private val movies = MutableLiveData<List<Movie>>()

    init {
        movies.observeForever {
            saveMovies(it)
        }
    }

    suspend fun getMovies(): LiveData<List<Movie>>{
        return withContext(Dispatchers.IO){
            fetchMovies()
            db.getMovieDao().getMovies()
        }
    }

    private suspend fun fetchMovies() {
        val lastSavedAt = prefs.getLastSavedAt()
        if (lastSavedAt == "" || isFetchNeeded(LocalDateTime.parse(lastSavedAt))) {
            val response = apiRequest { api.getMovies() }
            movies.postValue(response.results)
        }else{
            val response = apiRequest { api.getMovies() }
            movies.postValue(response.results)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun isFetchNeeded(savedAt: LocalDateTime): Boolean {
        return ChronoUnit.HOURS.between(savedAt,LocalDateTime.now()) > MINIMUM_INTERVAL
    }

    @SuppressLint("NewApi")
    private fun saveMovies(movies: List<Movie>) {
        Coroutines.io {
            prefs.saveLastSavedAt(LocalDateTime.now().toString())
            db.getMovieDao().saveAllMovies(movies)
        }
    }
}