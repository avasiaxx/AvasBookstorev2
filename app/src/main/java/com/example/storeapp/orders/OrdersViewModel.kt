package com.example.storeapp.orders

import androidx.lifecycle.MutableLiveData

class OrdersViewModel {
    private lateinit var orderList: List<OrderItem>
    private val _currentCart = MutableLiveData<List<OrderItem>?>(emptyList())


}