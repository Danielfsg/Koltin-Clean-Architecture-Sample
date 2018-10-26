package com.danielfsg.cleanarchitecture.features.movies.data

import com.danielfsg.cleanarchitecture.features.movies.domain.Movie

data class MovieEntity(private val id: Int, private val poster: String) {
    fun toMovie() = Movie(id, poster)
}