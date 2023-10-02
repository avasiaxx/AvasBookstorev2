package com.example.storeapp.domain

import com.example.storeapp.data.models.PaymentInfo
import javax.inject.Inject

class CCAsterisksFormatterImpl @Inject constructor() : CCAsterisksFormatter {

    override fun convertToAsterisks(paymentInfo: PaymentInfo): String {
        val ccNumber = paymentInfo.card
        return ccNumber.replaceRange(
            0, MAX_CHARACTER_COUNT,
            "*".repeat(MAX_CHARACTER_COUNT)
        )
    }

    companion object {
        private const val MAX_CHARACTER_COUNT = 12
    }
}