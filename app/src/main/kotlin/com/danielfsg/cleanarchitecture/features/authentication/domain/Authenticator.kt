package com.danielfsg.cleanarchitecture.features.authentication.domain

class Authenticator(private val prefsHandler: UserPrefs) {

    fun saveLoggedUser(user: User) {
        prefsHandler.loggedUser = user
        prefsHandler.jwToken = user.jwt
    }

    fun isUserLoggedIn() = !prefsHandler.loggedUser?.jwt.isNullOrEmpty()
}