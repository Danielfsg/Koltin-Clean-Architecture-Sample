package com.danielfsg.cleanarchitecture.features.movies.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

internal interface MoviesApi {

    companion object {
        private const val PARAM_MOVIE_ID = "movieID"
        private const val MOVIES = "movies.json"
        private const val MOVIE_DETAILS = "movie_0{$PARAM_MOVIE_ID}.json"
    }

    @GET(MOVIES)
    fun movies(): Call<List<MovieEntity>>

    @GET(MOVIE_DETAILS)
    fun movieDetails(@Path(PARAM_MOVIE_ID) movieId: Int): Call<MovieDetailsEntity>
}