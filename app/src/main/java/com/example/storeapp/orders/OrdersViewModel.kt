package com.example.storeapp.orders

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.storeapp.data.models.CartItem

class OrdersViewModel: ViewModel() {

    private lateinit var  orders: List<Any>
    fun newOrder(cartItem: CartItem, orderHeader: OrderHeader){

    }
}