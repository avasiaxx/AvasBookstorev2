package com.example.storeapp.domain.repositories

import com.example.storeapp.data.testdata.User

interface UserRepository {

    fun getUser(): User
}