package com.danielfsg.cleanarchitecture.features.authentication.data

import com.danielfsg.cleanarchitecture.features.authentication.domain.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

internal interface UserApi {

    companion object {
        const val LOGIN = "auth/local"
        const val REGISTER = "auth/local/register"
    }

    @POST(LOGIN)
    fun login(@Body user: User): Call<UserEntity>

    @POST(REGISTER)
    fun register(@Body user: User): Call<UserEntity>

}