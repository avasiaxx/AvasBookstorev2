package com.example.storeapp.data.models


data class PaymentInfo(
    val id: Int? = null,
    var card: String,
    val expiry: String,
    val ccv: Int
)
