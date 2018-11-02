package com.danielfsg.cleanarchitecture.features.authentication.data

import com.danielfsg.cleanarchitecture.features.authentication.domain.User
import retrofit2.Retrofit

class UserService(retrofit: Retrofit) : UserApi {

    private val userApi by lazy { retrofit.create(UserApi::class.java) }

    override fun login(user: User) = userApi.login(user)
    override fun register(user: User) = userApi.register(user)

}