package com.example.storeapp.data

import android.content.Context
import com.example.storeapp.data.models.Account
import com.example.storeapp.data.models.Cart
import com.example.storeapp.data.models.Login
import com.example.storeapp.data.models.Order
import com.example.storeapp.data.models.StoreItem
import com.example.storeapp.databinding.FragmentStoreItemBinding

interface Datasource {

    fun loadAccount(login: Login):Account
    fun loadCart(): Cart

    fun loadOrders(): List<Order>

    fun loadItems(binding: FragmentStoreItemBinding): List<StoreItem>
}