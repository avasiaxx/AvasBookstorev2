package com.example.storeapp.domain.repositories

import com.example.storeapp.data.models.Login

interface LoginRepository {

    fun login(username: String, password: String): String

    fun setLoggedInUser()

    fun logout()
}