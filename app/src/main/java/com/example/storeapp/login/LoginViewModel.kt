package com.example.storeapp.login

import androidx.lifecycle.ViewModel
import com.example.storeapp.domain.Validator
import com.example.storeapp.domain.repositories.AccountRepository
import com.example.storeapp.domain.repositories.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository,
    private val validator: Validator,
    private val accountRepository: AccountRepository
) : ViewModel() {

    private var loginState: LoginState = LoginState.PRE_LOGIN

    var statusMessage: String = "LOGIN MESSAGE"

    var isLoggedIn: Boolean = false

    companion object {
        enum class LoginState {
            PRE_LOGIN,
            INVALID_LOGIN,
            ACCEPTABLE_LOGIN
        }
    }

    fun validateLogin(username: String, password: String) {
        loginState = if (!validator.validUsername(username) ||
            !validator.validPassword(password)) {
            LoginState.INVALID_LOGIN
        } else {
            LoginState.ACCEPTABLE_LOGIN
        }
    }

    fun checkLoginState(username: String, password: String): String {
        statusMessage = when (loginState) {
            LoginState.PRE_LOGIN -> "PRE-LOGIN"
            LoginState.ACCEPTABLE_LOGIN -> attemptToLogin(username, password)
            LoginState.INVALID_LOGIN -> "Invalid Login"
        }
        return statusMessage
    }

    private fun attemptToLogin(username: String, password: String): String {
        val error = loginRepository.login(username, password)
        isLoggedIn = loginRepository.isLoggedIn
        if(loginRepository.isLoggedIn) {
            loginRepository.loggedInUser?.let { accountRepository.fetchAccountInfo(it) }
        }
        return error
    }
}