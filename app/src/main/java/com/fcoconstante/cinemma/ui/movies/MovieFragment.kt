package com.fcoconstante.cinemma.ui.movies

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.fcoconstante.cinemma.R
import com.fcoconstante.cinemma.data.db.entities.Movie
import com.fcoconstante.cinemma.util.Coroutines
import com.fcoconstante.cinemma.util.hide
import com.fcoconstante.cinemma.util.show
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.movie_fragment.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class MovieFragment : Fragment(), KodeinAware {

    override val kodein by kodein()
    private lateinit var viewModel: MovieViewModel
    private val factory : MoviesViewModelFactory by instance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movie_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this,factory).get(MovieViewModel::class.java)
        bindUI()
    }

    private fun bindUI() = Coroutines.main {
        progress_bar_movie.show()
        viewModel.movies.await().observe(this, Observer {
            progress_bar_movie.hide()

            initRecyclerView(it.toMovieItem())
        })
    }

    private fun initRecyclerView(quoteItem: List<MovieItem>) {
        val adapterQuote = GroupAdapter<ViewHolder>().apply {
            addAll(quoteItem)
        }

        recyclerMovies.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = adapterQuote
        }
    }

    private fun List<Movie>.toMovieItem(): List<MovieItem>{
        return this.map {
            MovieItem(it)
        }
    }

}
