package com.example.storeapp.domain.repositories


import com.example.storeapp.data.Datasource
import com.example.storeapp.data.models.Login
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val dataSource: Datasource
) : LoginRepository {

    override var loggedInUser: Login? = null
    override var newLogin: Login? = null
    override var isLoggedIn: Boolean = false

    override fun logout() {
        dataSource.logout()
    }

    override fun login(username: String, password: String): String {
        newLogin = Login(
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