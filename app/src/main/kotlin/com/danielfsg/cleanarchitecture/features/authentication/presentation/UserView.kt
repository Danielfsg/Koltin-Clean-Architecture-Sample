package com.danielfsg.cleanarchitecture.features.authentication.presentation

data class UserView(
    val username: String? = null,
    val email: String? = null,
    val password: String? = null,
    val identifier: String? = null
)