package com.example.storeapp.data.models

data class Address(
    val id: Int? = null,
    val streetName: String,
    val city: String,
    val province: String,
    val postalCode: String,
    val country: String
)
