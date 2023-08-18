package com.example.storeapp.cart

import com.example.storeapp.data.models.StoreItem


data class CartItem(
    var storeItem: StoreItem,
    var quantity: Int,
    var storeID: Int
) {

    fun createCart(
        storeItem: StoreItem
    ){
        this.storeItem = storeItem
    }
}