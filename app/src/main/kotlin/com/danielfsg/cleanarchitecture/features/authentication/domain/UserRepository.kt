package com.danielfsg.cleanarchitecture.features.authentication.domain

import com.danielfsg.cleanarchitecture.core.exception.Failure
import com.danielfsg.cleanarchitecture.core.functional.Either
import com.danielfsg.cleanarchitecture.core.platform.NetworkHandler
import com.danielfsg.cleanarchitecture.features.authentication.data.UserEntity
import com.danielfsg.cleanarchitecture.features.authentication.data.UserService

interface UserRepository {
    fun login(user: User): Either<Failure, User>
    fun register(user: User): Either<Failure, User>

    class Network(
        private val networkHandler: NetworkHandler,
        private val service: UserService
    ) : UserRepository {

        override fun login(user: User): Either<Failure, User> {
            return when (networkHandler.isConnected) {
                true -> {
                    networkHandler.request(service.login(user), { it.toUser() }, UserEntity.empty())
                }
                false, null -> Either.Left(Failure.NetworkConnection())
            }
        }

        override fun register(user: User): Either<Failure, User> {
            return when (networkHandler.isConnected) {
                true -> {
                    networkHandler.request(service.login(user), { it.toUser() }, UserEntity.empty())
                }
                false, null -> Either.Left(Failure.NetworkConnection())
            }
        }
    }
}