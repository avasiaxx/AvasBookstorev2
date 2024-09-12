package com.example.storeapp.domain.repositories

import com.example.storeapp.data.models.Login

interface LoginRepository {

    var loggedInUser: Login?
    var newLogin: Login?
    var isLoggedIn: Boolean

    fun login(username: String, password: String): String

    fun setLoggedInUser()

    fun logout()
}