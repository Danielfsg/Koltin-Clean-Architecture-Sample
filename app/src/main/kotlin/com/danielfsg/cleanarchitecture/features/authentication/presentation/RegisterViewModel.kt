package com.danielfsg.cleanarchitecture.features.authentication.presentation

import android.arch.lifecycle.MutableLiveData
import com.danielfsg.cleanarchitecture.core.platform.BaseViewModel
import com.danielfsg.cleanarchitecture.features.authentication.domain.Authenticator
import com.danielfsg.cleanarchitecture.features.authentication.domain.RegisterUser
import com.danielfsg.cleanarchitecture.features.authentication.domain.RegisterUser.Params
import com.danielfsg.cleanarchitecture.features.authentication.domain.User

class RegisterViewModel(private val registerUser: RegisterUser, private val authenticator: Authenticator) :
    BaseViewModel() {

    var successLiveData: MutableLiveData<Boolean> = MutableLiveData()

    fun registerUser(userView: UserView) =
        registerUser.execute({ it.either(::handleFailure, ::handleRegisterUser) }, Params(userView.toUser()))

    private fun handleRegisterUser(user: User) {
        authenticator.saveLoggedUser(user)
        successLiveData.value = authenticator.isUserLoggedIn()
    }

}