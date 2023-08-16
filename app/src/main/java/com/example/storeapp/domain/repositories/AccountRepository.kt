package com.example.storeapp.domain.repositories

import com.example.storeapp.data.NetworkDatasource
import com.example.storeapp.data.models.Login
import javax.inject.Inject

class AccountRepository {
    @Inject
    lateinit var networkDatasource: NetworkDatasource
    fun fetchAccountInfo(login: Login) {
    }
}