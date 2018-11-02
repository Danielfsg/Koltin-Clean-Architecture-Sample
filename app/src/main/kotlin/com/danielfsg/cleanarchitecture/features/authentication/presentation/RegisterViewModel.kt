package com.danielfsg.cleanarchitecture.features.authentication.presentation

import android.arch.lifecycle.MutableLiveData
import com.danielfsg.cleanarchitecture.core.platform.BaseViewModel
import com.danielfsg.cleanarchitecture.features.authentication.domain.Authenticator
import com.danielfsg.cleanarchitecture.features.authentication.domain.LoginUser
import com.danielfsg.cleanarchitecture.features.authentication.domain.LoginUser.Params
import com.danielfsg.cleanarchitecture.features.authentication.domain.User

class RegisterViewModel(private val loginUser: LoginUser, private val authenticator: Authenticator) : BaseViewModel() {

    var loggedUser: MutableLiveData<Boolean> = MutableLiveData()

    fun registerUser(userView: UserView) {
        val user = User(identifier = userView.email, password = userView.password)
        loginUser.execute({ it.either(::handleFailure, ::handleRegisterUser) }, Params(user))
    }

    private fun handleRegisterUser(user: User) {
        authenticator.saveLoggedUser(user)
        loggedUser.value = authenticator.isUserLoggedIn()
    }

}