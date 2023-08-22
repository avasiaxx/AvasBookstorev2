package com.example.storeapp.domain

interface CurrencyFormatter {
    fun formatCurrency(input: Double): String
}