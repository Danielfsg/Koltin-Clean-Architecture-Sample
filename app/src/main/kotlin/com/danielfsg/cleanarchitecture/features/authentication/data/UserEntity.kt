package com.danielfsg.cleanarchitecture.features.authentication.data

import com.danielfsg.cleanarchitecture.core.extension.empty
import com.danielfsg.cleanarchitecture.features.authentication.domain.User

data class UserEntity(
    private val jwt: String,
    private val user: UserContentEntity
) {
    data class UserContentEntity(
        val _id: String,
        val username: String,
        val email: String,
        val provider: String
    )

    fun toUser() = User(jwt, user._id, user.username, null, user.email, user.provider)

    companion object {
        fun empty() = UserEntity(
            String.empty(),
            UserContentEntity(
                String.empty(),
                String.empty(),
                String.empty(),
                String.empty()
            )
        )
    }
}