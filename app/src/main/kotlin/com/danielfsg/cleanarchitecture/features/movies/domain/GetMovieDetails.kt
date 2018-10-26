package com.danielfsg.cleanarchitecture.features.movies.domain

import com.danielfsg.cleanarchitecture.core.interactor.UseCase
import com.danielfsg.cleanarchitecture.features.movies.domain.GetMovieDetails.Params

class GetMovieDetails(private val moviesRepository: MoviesRepository) : UseCase<MovieDetails, Params>() {

    override suspend fun run(params: Params) = moviesRepository.movieDetails(params.id)

    data class Params(val id: Int)

}