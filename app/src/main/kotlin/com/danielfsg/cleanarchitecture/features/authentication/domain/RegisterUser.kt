package com.danielfsg.cleanarchitecture.features.authentication.domain

import com.danielfsg.cleanarchitecture.core.interactor.UseCase
import com.danielfsg.cleanarchitecture.features.authentication.domain.RegisterUser.Params

class RegisterUser(private val userRepository: UserRepository) : UseCase<User, Params>() {

    override suspend fun run(params: Params) = userRepository.register(params.user)

    data class Params(val user: User)
}