package com.danielfsg.cleanarchitecture.features.movies.domain

import android.content.Context
import com.danielfsg.cleanarchitecture.core.exception.Failure
import com.danielfsg.cleanarchitecture.core.functional.Either
import com.danielfsg.cleanarchitecture.core.functional.Either.Right
import com.danielfsg.cleanarchitecture.core.interactor.UseCase
import com.danielfsg.cleanarchitecture.core.interactor.UseCase.None
import com.danielfsg.cleanarchitecture.core.navigation.Navigator
import com.danielfsg.cleanarchitecture.features.movies.domain.PlayMovie.Params

class PlayMovie(
    private val context: Context,
    private val navigator: Navigator
) : UseCase<None, Params>() {

    override suspend fun run(params: Params): Either<Failure, None> {
        navigator.openVideo(context, params.url)
        return Right(None())
    }

    data class Params(val url: String)
}