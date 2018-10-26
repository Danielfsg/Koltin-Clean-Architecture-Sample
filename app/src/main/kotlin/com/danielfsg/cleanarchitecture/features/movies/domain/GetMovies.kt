package com.danielfsg.cleanarchitecture.features.movies.domain

import com.danielfsg.cleanarchitecture.core.interactor.UseCase
import com.danielfsg.cleanarchitecture.core.interactor.UseCase.None

class GetMovies(private val moviesRepository: MoviesRepository) : UseCase<List<Movie>, None>() {

    override suspend fun run(params: None) = moviesRepository.movies()

}