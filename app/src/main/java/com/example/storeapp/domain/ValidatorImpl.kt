package com.example.storeapp.domain

import javax.inject.Inject

class ValidatorImpl @Inject constructor() : Validator {

    override fun checkCreditCard(cc: String): Boolean {
        return cc.length == 16
    }

    override fun checkCCV(ccv: String): Boolean {
        return ccv.length == 3
    }

    override fun validPassword(password: String): Boolean {
        return password.length > 5
    }

    override fun validUsername(username: String): Boolean {
        return username.length >= 5
    }
}