package com.danielfsg.cleanarchitecture.features.authentication.presentation

import com.danielfsg.cleanarchitecture.features.authentication.domain.User

data class UserView(
    val username: String? = null,
    val email: String? = null,
    val password: String? = null,
    val identifier: String? = null
) {
    fun toUser() = User(username = username, email = email, password = password, identifier = identifier)
}