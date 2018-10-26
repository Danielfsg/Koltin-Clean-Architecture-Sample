package com.danielfsg.cleanarchitecture.features.movies.presentation

import android.arch.lifecycle.MutableLiveData
import com.danielfsg.cleanarchitecture.core.interactor.UseCase.None
import com.danielfsg.cleanarchitecture.core.platform.BaseViewModel
import com.danielfsg.cleanarchitecture.features.movies.domain.GetMovies
import com.danielfsg.cleanarchitecture.features.movies.domain.Movie

class MoviesViewModel(private val getMovies: GetMovies) : BaseViewModel() {

    var movies: MutableLiveData<List<MovieView>> = MutableLiveData()

    fun loadMovies() = getMovies.execute({ it.either(::handleFailure, ::handleMovieList) }, None())

    private fun handleMovieList(movies: List<Movie>) {
        this.movies.value = movies.map { MovieView(it.id, it.poster) }
    }
}