package com.fcoconstante.cinemma.ui.movies

import com.fcoconstante.cinemma.R
import com.fcoconstante.cinemma.data.db.entities.Movie
import com.fcoconstante.cinemma.databinding.ItemMovieBinding
import com.xwray.groupie.databinding.BindableItem
import com.xwray.groupie.databinding.ViewHolder

class MovieItem(
    private val movie : Movie
) : BindableItem<ItemMovieBinding>(){

    override fun getLayout() = R.layout.item_movie

    override fun bind(viewBinding: ItemMovieBinding, position: Int) {
        viewBinding.movie = movie
        viewBinding.imageUrl = "https://image.tmdb.org/t/p/w500${movie.poster_path}"

    }

    override fun bind(viewHolder: ViewHolder<ItemMovieBinding>, position: Int) {
        super.bind(viewHolder, position)

    }
}