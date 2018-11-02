package com.danielfsg.cleanarchitecture.features.movies.domain

import com.danielfsg.cleanarchitecture.core.exception.Failure
import com.danielfsg.cleanarchitecture.core.exception.Failure.NetworkConnection
import com.danielfsg.cleanarchitecture.core.functional.Either
import com.danielfsg.cleanarchitecture.core.functional.Either.Left
import com.danielfsg.cleanarchitecture.core.platform.NetworkHandler
import com.danielfsg.cleanarchitecture.features.movies.data.MovieDetailsEntity
import com.danielfsg.cleanarchitecture.features.movies.data.MoviesService

interface MoviesRepository {
    fun movies(): Either<Failure, List<Movie>>
    fun movieDetails(movieId: Int): Either<Failure, MovieDetails>

    class Network(
        private val networkHandler: NetworkHandler,
        private val service: MoviesService
    ) : MoviesRepository {

        override fun movies(): Either<Failure, List<Movie>> {
            return when (networkHandler.isConnected) {
                true -> networkHandler.request(service.movies(), { it -> it.map { it.toMovie() } }, emptyList())
                false, null -> Left(NetworkConnection())
            }
        }

        override fun movieDetails(movieId: Int): Either<Failure, MovieDetails> {
            return when (networkHandler.isConnected) {
                true -> networkHandler.request(
                    service.movieDetails(movieId), { it.toMovieDetails() },
                    MovieDetailsEntity.empty()
                )
                false, null -> Left(NetworkConnection())
            }
        }
    }
}