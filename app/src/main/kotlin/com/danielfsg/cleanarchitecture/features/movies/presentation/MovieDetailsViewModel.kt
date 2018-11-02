package com.danielfsg.cleanarchitecture.features.movies.presentation

import android.arch.lifecycle.MutableLiveData
import com.danielfsg.cleanarchitecture.core.platform.BaseViewModel
import com.danielfsg.cleanarchitecture.features.movies.domain.GetMovieDetails
import com.danielfsg.cleanarchitecture.features.movies.domain.GetMovieDetails.Params
import com.danielfsg.cleanarchitecture.features.movies.domain.MovieDetails
import com.danielfsg.cleanarchitecture.features.movies.domain.PlayMovie

class MovieDetailsViewModel(
    private val getMovieDetails: GetMovieDetails,
    private val playMovie: PlayMovie
) : BaseViewModel() {

    var movieDetails: MutableLiveData<MovieDetailsView> = MutableLiveData()

    fun loadMovieDetails(movieId: Int) =
        getMovieDetails.execute({ it.either(::handleFailure, ::handleMovieDetails) }, Params(movieId))

    fun playMovie(url: String) = playMovie.execute({}, PlayMovie.Params(url))

    private fun handleMovieDetails(movie: MovieDetails) {
        this.movieDetails.value = MovieDetailsView(
            movie.id,
            movie.title,
            movie.poster,
            movie.summary,
            movie.cast,
            movie.director,
            movie.year,
            movie.trailer
        )
    }
}