package com.danielfsg.cleanarchitecture.features.movies.presentation

import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import com.danielfsg.cleanarchitecture.R
import com.danielfsg.cleanarchitecture.core.exception.Failure
import com.danielfsg.cleanarchitecture.core.exception.Failure.NetworkConnection
import com.danielfsg.cleanarchitecture.core.exception.Failure.ServerError
import com.danielfsg.cleanarchitecture.core.extension.failure
import com.danielfsg.cleanarchitecture.core.extension.invisible
import com.danielfsg.cleanarchitecture.core.extension.observe
import com.danielfsg.cleanarchitecture.core.extension.visible
import com.danielfsg.cleanarchitecture.core.navigation.Navigator
import com.danielfsg.cleanarchitecture.core.platform.BaseFragment
import com.danielfsg.cleanarchitecture.features.movies.domain.MovieFailure.ListNotAvailable
import kotlinx.android.synthetic.main.fragment_movies.*
import org.koin.android.ext.android.inject

class MoviesFragment : BaseFragment() {

    private val navigator: Navigator by inject()
    private val moviesAdapter: MoviesAdapter by inject()
    private val moviesViewModel: MoviesViewModel by inject()

    override fun layoutId() = R.layout.fragment_movies

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(moviesViewModel) {
            observe(movies, ::renderMoviesList)
            failure(failure, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()
        loadMoviesList()
    }

    private fun initializeView() {
        movieList.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        movieList.adapter = moviesAdapter
        moviesAdapter.clickListener = { movie, navigationExtras ->
            navigator.showMovieDetails(activity!!, movie, navigationExtras)
        }
    }

    private fun loadMoviesList() {
        emptyView.invisible()
        movieList.visible()
        showProgress()
        moviesViewModel.loadMovies()
    }

    private fun renderMoviesList(movies: List<MovieView>?) {
        moviesAdapter.collection = movies.orEmpty()
        hideProgress()
    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            is NetworkConnection -> renderFailure(R.string.failure_network_connection)
            is ServerError -> renderFailure(R.string.failure_network_connection)
            is ListNotAvailable -> renderFailure(R.string.failure_network_connection)
        }
    }

    private fun renderFailure(@StringRes message: Int) {
        movieList.invisible()
        emptyView.visible()
        hideProgress()
        notifyWithAction(message, R.string.action_refresh, ::loadMoviesList)
    }
}
