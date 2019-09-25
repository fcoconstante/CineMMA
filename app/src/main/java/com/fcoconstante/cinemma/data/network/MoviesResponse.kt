package com.fcoconstante.cinemma.data.network

import com.fcoconstante.cinemma.data.db.entities.Movie

class MoviesResponse (
    val page : Int,
    val total_results : Int,
    val total_pages : Int,
    val results: List<Movie>
)