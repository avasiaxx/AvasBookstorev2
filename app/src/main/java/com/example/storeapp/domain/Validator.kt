package com.example.storeapp.domain

interface Validator {
    fun checkCreditCard(cc: String): Boolean

    fun checkCCV(ccv: String): Boolean

    fun validPassword(password: String): Boolean

    fun validUsername(username: String): Boolean
}