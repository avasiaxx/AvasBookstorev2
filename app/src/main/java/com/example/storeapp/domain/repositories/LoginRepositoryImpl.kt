package com.example.storeapp.domain.repositories


import com.example.storeapp.data.Datasource
import com.example.storeapp.data.models.Login
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val dataSource: Datasource
) : LoginRepository {

    private var loggedInUser: Login? = null
    private var newLogin: Login? = null
    private var isLoggedIn: Boolean = false

    override fun logout() {
        dataSource.logout()
    }

    override fun login(username: String, password: String): String {
        val newLogin = Login(
            username,
            password
        )
        return if (dataSource.login(newLogin)) {
            setLoggedInUser()
            "Login successful"
        } else {
            "Login does not exist"
        }
    }

    override fun setLoggedInUser() {
        loggedInUser = newLogin
        isLoggedIn = true
        newLogin = null
    }
}