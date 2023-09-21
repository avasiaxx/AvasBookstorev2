package com.example.storeapp.data

import com.example.storeapp.data.models.Account
import com.example.storeapp.data.models.Cart
import com.example.storeapp.data.models.Login
import com.example.storeapp.data.models.Order
import com.example.storeapp.data.models.PaymentInfo
import com.example.storeapp.data.models.StoreItem

interface Datasource {

    fun loadAccount(login: Login):Account
    fun loadCart(): Cart

    fun loadOrders(): List<Order>

    fun loadItems(): List<StoreItem>

    fun loadPaymentInfo(): List<PaymentInfo>

    fun getOrder(): Order
}