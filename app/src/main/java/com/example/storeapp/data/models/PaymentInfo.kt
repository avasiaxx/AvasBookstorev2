package com.example.storeapp.data.models


data class PaymentInfo(
    val id: Int? = null,
    val card: String,
    val expiry: String,
    val cvv: Int
)
