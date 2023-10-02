package com.example.storeapp.login

import androidx.lifecycle.ViewModel
import com.example.storeapp.domain.Validator
import com.example.storeapp.domain.repositories.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository,
    private val validator: Validator
) : ViewModel() {

    private var loginState: LoginState = LoginState.PRE_LOGIN

    private var statusMessage: String = "LOGIN MESSAGE"

    companion object {
        enum class LoginState {
            PRE_LOGIN,
            INVALID_PASSWORD,
            INVALID_USERNAME,
            ACCEPTABLE_LOGIN
        }
    }

    fun validateLogin(username: String, password: String) {
        loginState = if (!validator.validUsername(username)) {
            LoginState.INVALID_USERNAME
        } else if (!validator.validPassword(password)) {
            LoginState.INVALID_PASSWORD
        } else {
            LoginState.ACCEPTABLE_LOGIN
        }
    }

    fun checkLoginState(username: String, password: String): String {
        statusMessage = when (loginState) {
            LoginState.PRE_LOGIN -> "PRE-LOGIN"
            LoginState.ACCEPTABLE_LOGIN -> loginRepository.login(username, password)
            LoginState.INVALID_USERNAME -> "INVALID USERNAME"
            LoginState.INVALID_PASSWORD -> "INVALID PASSWORD"
        }
        return statusMessage
    }
}