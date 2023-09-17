package com.example.storeapp.domain

import javax.inject.Inject

class ValidatorImpl @Inject constructor(): Validator {

    override fun checkCreditCard(cc: String): Boolean {
        return cc.length == 16
    }

    override fun checkCCV(ccv: String): Boolean {
        return ccv.length == 3
    }
}