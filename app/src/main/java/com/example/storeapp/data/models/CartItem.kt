package com.example.storeapp.data.models

data class CartItem(
    val id: Int? = null,
    val storeItem: StoreItem,
    val quantity: Int
)
