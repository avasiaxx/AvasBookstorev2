package com.example.storeapp.data.models


data class Account(
    val id: Int? = null,
    val person: Person,
    val primaryAddress: Address,
    val primaryPaymentInfo: PaymentInfo,
    val userName: String,
    val isCustomer: Boolean,
    val isManager: Boolean
)
