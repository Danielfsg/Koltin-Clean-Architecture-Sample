package com.danielfsg.cleanarchitecture.features.movies.domain

import com.danielfsg.cleanarchitecture.core.exception.Failure
import com.danielfsg.cleanarchitecture.core.exception.Failure.NetworkConnection
import com.danielfsg.cleanarchitecture.core.exception.Failure.ServerError
import com.danielfsg.cleanarchitecture.core.functional.Either
import com.danielfsg.cleanarchitecture.core.functional.Either.Left
import com.danielfsg.cleanarchitecture.core.functional.Either.Right
import com.danielfsg.cleanarchitecture.core.platform.NetworkHandler
import com.danielfsg.cleanarchitecture.features.movies.data.MovieDetailsEntity
import com.danielfsg.cleanarchitecture.features.movies.data.MoviesService
import retrofit2.Call

interface MoviesRepository {
    fun movies(): Either<Failure, List<Movie>>
    fun movieDetails(movieId: Int): Either<Failure, MovieDetails>

    class Network(
        private val networkHandler: NetworkHandler,
        private val service: MoviesService
    ) : MoviesRepository {

        override fun movies(): Either<Failure, List<Movie>> {
            return when (networkHandler.isConnected) {
                true -> request(service.movies(), { it -> it.map { it.toMovie() } }, emptyList())
                false, null -> Left(NetworkConnection())
            }
        }

        override fun movieDetails(movieId: Int): Either<Failure, MovieDetails> {
            return when (networkHandler.isConnected) {
                true -> request(service.movieDetails(movieId), { it.toMovieDetails() },
                    MovieDetailsEntity.empty()
                )
                false, null -> Left(NetworkConnection())
            }
        }

        private fun <T, R> request(call: Call<T>, transform: (T) -> R, default: T): Either<Failure, R> {
            return try {
                val response = call.execute()
                when (response.isSuccessful) {
                    true -> Right(transform((response.body() ?: default)))
                    false -> Left(ServerError())
                }
            } catch (exception: Throwable) {
                Left(ServerError())
            }
        }
    }
}