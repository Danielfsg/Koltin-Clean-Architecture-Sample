package com.danielfsg.cleanarchitecture.features.movies.data

import retrofit2.Retrofit

class MoviesService(retrofit: Retrofit) : MoviesApi {

    private val moviesApi by lazy { retrofit.create(MoviesApi::class.java) }

    override fun movies() = moviesApi.movies()
    override fun movieDetails(movieId: Int) = moviesApi.movieDetails(movieId)
}