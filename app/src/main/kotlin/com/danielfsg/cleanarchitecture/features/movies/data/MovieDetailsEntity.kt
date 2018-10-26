package com.danielfsg.cleanarchitecture.features.movies.data

import com.danielfsg.cleanarchitecture.core.extension.empty
import com.danielfsg.cleanarchitecture.features.movies.domain.MovieDetails

data class MovieDetailsEntity(
    private val id: Int,
    private val title: String,
    private val poster: String,
    private val summary: String,
    private val cast: String,
    private val director: String,
    private val year: Int,
    private val trailer: String
) {
    companion object {
        fun empty() = MovieDetailsEntity(
            0,
            String.empty(),
            String.empty(),
            String.empty(),
            String.empty(),
            String.empty(),
            0,
            String.empty()
        )
    }

    fun toMovieDetails() = MovieDetails(
        id,
        title,
        poster,
        summary,
        cast,
        director,
        year,
        trailer
    )
}