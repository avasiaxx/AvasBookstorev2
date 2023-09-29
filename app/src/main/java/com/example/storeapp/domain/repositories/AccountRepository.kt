package com.example.storeapp.domain.repositories

import com.example.storeapp.data.models.Account
import com.example.storeapp.data.models.Login

interface AccountRepository {

    val account: Account
    fun fetchAccountInfo(login: Login)
}