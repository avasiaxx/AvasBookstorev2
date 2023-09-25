package com.example.storeapp.data.models

data class Cart(
    val id: Int? = null,
    var items: List<CartItem>
)
