package com.danielfsg.cleanarchitecture.features.authentication.domain

import com.danielfsg.cleanarchitecture.core.extension.empty

data class User(
    val jwt: String? = null,
    val _id: String? = null,
    val username: String? = null,
    val password: String? = null,
    val email: String? = null,
    val provider: String? = null,
    val identifier: String? = null
) {
    companion object {
        fun empty() = User(
            String.empty(),
            String.empty(),
            String.empty(),
            String.empty(),
            String.empty(),
            String.empty(),
            String.empty()
        )
    }
}