package com.example.storeapp.orders

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.storeapp.cart.CartItem

class OrdersViewModel: ViewModel() {

    private lateinit var  orders: List<Any>
    fun newOrder(cartItem: CartItem, orderHeader: OrderHeader){

    }
}