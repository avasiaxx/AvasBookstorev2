package com.example.storeapp.checkout

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.storeapp.data.models.CartItem
import com.example.storeapp.data.models.Order
import com.example.storeapp.domain.repositories.OrderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CheckOutViewModel @Inject constructor(
    private var orderRepository: OrderRepository
): ViewModel(){

    private lateinit var order: Order

    private lateinit var cartItems: List<CartItem>
    private val _currentCartItems = MutableLiveData<List<CartItem>?>(emptyList())
    val items: LiveData<List<CartItem>?> get() = _currentCartItems

    fun init(){
        order = orderRepository.loadOrder()
        cartItems = order.cart.items
        _currentCartItems.value = cartItems
    }
}