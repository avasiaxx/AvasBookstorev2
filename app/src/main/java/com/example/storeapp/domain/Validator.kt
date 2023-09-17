package com.example.storeapp.domain

interface Validator {
    fun checkCreditCard(cc: String): Boolean

    fun checkCCV(ccv: String): Boolean
}