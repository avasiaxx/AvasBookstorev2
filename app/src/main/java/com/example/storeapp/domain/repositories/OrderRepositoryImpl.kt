package com.example.storeapp.domain.repositories

import com.example.storeapp.data.models.Cart
import com.example.storeapp.data.models.Order
import javax.inject.Inject

class OrderRepositoryImpl @Inject constructor(

): OrderRepository {

    private lateinit var newOrder: Order
    override fun createOrder(
        id: Int?,
        cart:Cart,
        orderDate: String,
        subTotal: Double,
        taxAmount: Double,
        total: Double
    ) {
        newOrder = Order(
            id,
            cart,
            orderDate,
            subTotal,
            taxAmount,
            total
        )
    }

    override fun loadOrder(): Order = newOrder
}