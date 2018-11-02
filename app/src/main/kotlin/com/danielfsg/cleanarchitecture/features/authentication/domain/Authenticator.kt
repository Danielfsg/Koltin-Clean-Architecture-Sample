package com.danielfsg.cleanarchitecture.features.authentication.domain

class Authenticator(private val userPrefs: UserPrefs) {

    fun saveLoggedUser(user: User) {
        userPrefs.loggedUser = user
    }

    fun isUserLoggedIn() = !userPrefs.loggedUser?.jwt.isNullOrEmpty()
}