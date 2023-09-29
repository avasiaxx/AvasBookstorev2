package com.example.storeapp.domain.repositories

import com.example.storeapp.data.Datasource
import com.example.storeapp.data.models.Account
import com.example.storeapp.data.models.Login
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    val datasource: Datasource
): AccountRepository {

    override lateinit var account: Account
    override fun fetchAccountInfo(login: Login) {
        account = datasource.loadAccount(login)
    }
}