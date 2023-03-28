package com.example.storeapp.cart

import com.example.storeapp.store.StoreItem
import android.content.Context

class CartItem(
    val quantity: Int,
    val cost: Double,
    val storeItem: StoreItem
) {
    companion object
    {
        fun setData(
            quantity: Int,
            cost: Double,
            storeItem: StoreItem
        ) = CartItem(
                quantity = quantity,
                storeItem = storeItem,
                cost = cost
                )
    }
}