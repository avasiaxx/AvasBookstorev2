package com.example.storeapp.shared

import android.icu.text.NumberFormat
import android.icu.util.Currency
import com.example.storeapp.shared.Util.formatDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Locale

object Util {

    //Extension Function
    fun Double.formatCurrency(): String {
        val format: NumberFormat = NumberFormat.getCurrencyInstance()
        format.maximumFractionDigits = 2
        format.currency = Currency.getInstance("CAD")
        return format.format(this)
    }

    //DateTime Formatter
    fun LocalDateTime.formatDate(): String {
        val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
            .withLocale(Locale.getDefault())
        return formatter.format(this)
    }
}