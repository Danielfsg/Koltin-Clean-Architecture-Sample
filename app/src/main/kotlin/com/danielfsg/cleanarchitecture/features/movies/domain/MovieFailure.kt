package com.danielfsg.cleanarchitecture.features.movies.domain

import com.danielfsg.cleanarchitecture.core.exception.Failure.FeatureFailure

class MovieFailure {
    class ListNotAvailable: FeatureFailure()
    class NonExistentMovie: FeatureFailure()
}