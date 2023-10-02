package com.example.storeapp.domain

import android.icu.text.NumberFormat
import android.icu.util.Currency
import javax.inject.Inject

class CurrencyFormatterImpl @Inject constructor() : CurrencyFormatter {
    override fun formatCurrency(input: Double): String {
        val format: NumberFormat = NumberFormat.getCurrencyInstance()
        format.maximumFractionDigits = 2
        format.currency = Currency.getInstance("CAD")
        return format.format(input)
    }
}