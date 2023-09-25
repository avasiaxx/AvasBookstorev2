package com.example.storeapp.domain.repositories

import com.example.storeapp.data.models.Cart
import com.example.storeapp.data.models.CartItem

interface CartRepository {

    fun updateCart(cartItems: MutableList<CartItem>?): Cart

    fun loadCart(): Cart
}