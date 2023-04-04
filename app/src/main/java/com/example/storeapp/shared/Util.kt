package com.example.storeapp.shared

import android.icu.text.NumberFormat
import android.icu.util.Currency

object Util {

    //Extension Function
    fun Double.formatCurrency(): String {
        val format: NumberFormat = NumberFormat.getCurrencyInstance()
        format.maximumFractionDigits = 2
        format.currency = Currency.getInstance("CAD")
        return format.format(this)
    }
}