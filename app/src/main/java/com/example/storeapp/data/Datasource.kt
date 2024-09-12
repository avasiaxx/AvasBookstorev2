package com.example.storeapp.data

import com.example.storeapp.data.models.Account
import com.example.storeapp.data.models.Address
import com.example.storeapp.data.models.Cart
import com.example.storeapp.data.models.Login
import com.example.storeapp.data.models.Order
import com.example.storeapp.data.models.PaymentInfo
import com.example.storeapp.data.models.Person
import com.example.storeapp.data.models.StoreItem

interface Datasource {

    fun loadAccount(login: Login): Account

    fun loadAddress(): Address

    fun loadPerson(): Person
    fun loadCart(): Cart

    fun loadOrders(): List<Order>

    fun loadItems(): List<StoreItem>

    fun loadPaymentInfo(): List<PaymentInfo>

    fun loadTestLogin(): Login

    fun getOrder(): Order

    fun login(login: Login?): Boolean

    fun logout()
}