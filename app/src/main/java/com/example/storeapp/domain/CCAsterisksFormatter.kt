package com.example.storeapp.domain

import com.example.storeapp.data.models.PaymentInfo

interface CCAsterisksFormatter {

    fun convertToAsterisks(paymentInfo: PaymentInfo): String
}