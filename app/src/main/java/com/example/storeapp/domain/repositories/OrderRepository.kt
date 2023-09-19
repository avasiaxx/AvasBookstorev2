package com.example.storeapp.domain.repositories

import com.example.storeapp.data.models.Cart
import com.example.storeapp.data.models.Order

interface OrderRepository {

    fun createOrder(id: Int?, cart: Cart, orderDate: String, subTotal: Double, taxAmount: Double,
                    total: Double): Order
}