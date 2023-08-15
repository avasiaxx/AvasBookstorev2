package com.example.storeapp.orders

import android.content.Context
import androidx.annotation.StringRes
import com.example.storeapp.cart.CartItem

data class OrderHeader(
    var deliveryTime: String,
    var total: Double,
    var shippingTo: String,
    var orderNum: Int,
) {
    companion object {
        fun getOrderData(
            context: Context,
            @StringRes deliveryTimeId: Int,
            total: Double,
            @StringRes shippingToId: Int,
            orderNum: Int
        ) = OrderHeader (
            deliveryTime = context.getString(deliveryTimeId),
            total = total,
            shippingTo = context.getString(shippingToId),
            orderNum = orderNum
        )
    }
}