package com.danielfsg.cleanarchitecture.features.movies.domain

import com.danielfsg.cleanarchitecture.core.extension.empty

data class Movie(val id: Int, val poster: String) {
    companion object {
        fun empty() = Movie(0, String.empty())
    }
}