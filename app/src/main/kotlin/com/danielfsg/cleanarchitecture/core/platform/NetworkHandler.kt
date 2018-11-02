package com.danielfsg.cleanarchitecture.core.platform

import android.content.Context
import com.danielfsg.cleanarchitecture.core.exception.Failure
import com.danielfsg.cleanarchitecture.core.extension.networkInfo
import com.danielfsg.cleanarchitecture.core.functional.Either
import retrofit2.Call

class NetworkHandler(private val context: Context) {
    val isConnected get() = context.networkInfo?.isConnectedOrConnecting

    fun <T, R> request(call: Call<T>, transform: (T) -> R, default: T): Either<Failure, R> {
        return try {
            val response = call.execute()
            when (response.isSuccessful) {
                true -> Either.Right(transform((response.body() ?: default)))
                false -> Either.Left(Failure.ServerError(response.errorBody()?.charStream().toString()))
            }
        } catch (exception: Throwable) {
            Either.Left(Failure.ServerError(exception.message.toString()))
        }
    }
}