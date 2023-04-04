package com.example.storeapp.cart

import com.example.storeapp.store.StoreItem

data class CartItem(
    var storeItem: StoreItem,
    var quantity: Int
) {

    fun createCart(
        storeItem: StoreItem
    ){
        this.storeItem = storeItem
    }
}