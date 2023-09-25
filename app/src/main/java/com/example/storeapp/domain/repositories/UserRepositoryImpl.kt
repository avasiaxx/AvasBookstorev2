package com.example.storeapp.domain.repositories

import com.example.storeapp.data.Datasource
import com.example.storeapp.data.testdata.User
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    val datasource: Datasource
): UserRepository{

    private val user: User = User(datasource)
    override fun getUser(): User {
        return user
    }
}