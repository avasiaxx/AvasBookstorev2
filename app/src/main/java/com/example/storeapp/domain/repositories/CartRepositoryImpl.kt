package com.example.storeapp.domain.repositories

import com.example.storeapp.data.models.Cart
import com.example.storeapp.data.models.CartItem
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
): CartRepository {

    private var cart: Cart = Cart(
        items = emptyList()
    )
    override fun updateCart(cartItems: MutableList<CartItem>?): Cart {
        if (cartItems != null) {
            cart.items = cartItems
        }
        return cart
    }

    override fun loadCart() : Cart{
        return cart
    }
}