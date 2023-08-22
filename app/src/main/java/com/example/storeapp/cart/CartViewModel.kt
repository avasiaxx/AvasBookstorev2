package com.example.storeapp.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.storeapp.data.models.CartItem

class CartViewModel: ViewModel() {

    private val _currentCart = MutableLiveData<List<CartItem>?>(emptyList())
    val items: LiveData<List<CartItem>?>
        get() = _currentCart

    private val _subTotal = MutableLiveData<Double>()
    val subTotal: LiveData<Double>
        get() = _subTotal

    private val taxAmount: Double = .13
    private val _tax = MutableLiveData<Double>()
    val tax: LiveData<Double>
        get() = _tax

    private val _totalPrice = MutableLiveData<Double>()
    val totalPrice: LiveData<Double>
        get() = _totalPrice

    fun onIncrease(cartItem: CartItem) {
            val items = _currentCart.value?.toMutableList()
            val item = items?.firstOrNull { it.storeItem.name == cartItem.storeItem.name }

            if (item == null) {
                items?.add(cartItem)
            }else if(item.quantity > 0){
                val index = items.indexOf(item)
                items.remove(item)
                items.add(
                    index,
                    item.copy(quantity = item.quantity.plus(1))
                )
            }
        _currentCart.value = items
        updatePrices()
    }

    fun onDecrease(cartItem: CartItem){
            val items = _currentCart.value?.toMutableList() ?: return
            val item = items.firstOrNull { cartItem.storeItem.name == it.storeItem.name }
            val index = items.indexOf(item)
            items.remove(item)
        if (item != null && item.quantity > 1) {
            items.add(
                index,
                item.copy(quantity = item.quantity.minus(1))
            )
        }
        _currentCart.value = items
        updatePrices()
    }

    fun removeFromCart(cartItem: CartItem){
        val items = _currentCart.value?.toMutableList() ?: return
        items.remove(cartItem)
        _currentCart.value = items
        updatePrices()
    }

    private fun updatePrices() {
        _subTotal.value = _currentCart.value?.sumOf { it.storeItem.cost * it.quantity }
        _tax.value = taxAmount * _subTotal.value!!
        _totalPrice.value = _tax.value?.let { _subTotal.value?.plus(it) }
    }
}