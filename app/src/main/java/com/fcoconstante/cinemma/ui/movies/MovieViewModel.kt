package com.fcoconstante.cinemma.ui.movies

import androidx.lifecycle.ViewModel
import com.fcoconstante.cinemma.data.repositories.MoviesRepository
import com.fcoconstante.cinemma.util.lazyDeferred

class MovieViewModel(
    repository: MoviesRepository
) : ViewModel() {
    val movies by lazyDeferred {
        repository.getMovies()
    }
}
