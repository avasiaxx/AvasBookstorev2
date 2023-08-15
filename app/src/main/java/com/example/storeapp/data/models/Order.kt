package com.example.storeapp.data.models

import com.example.storeapp.data.models.Cart

data class Order(
    val id: Int? = null,
    val cart: Cart,
    val orderDate: String,
    val subTotal: Double,
    val taxAmount: Double,
    val total: Double
)
