package com.example.storeapp.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.storeapp.store.StoreItem

class CartViewModel: ViewModel() {

    private lateinit var originalCart: List<CartItem>
    private val _currentCart = MutableLiveData<List<CartItem>?>(emptyList())
    val items: LiveData<List<CartItem>?>
        get() = _currentCart

    private val _subTotal = MutableLiveData<Double>()
    val subTotal: LiveData<Double>
        get() = _subTotal

    private val taxAmount: Double = .13
    private val _Tax = MutableLiveData<Double>()
    val Tax: LiveData<Double>
        get() = _Tax

    private val _TotalPrice = MutableLiveData<Double>()
    val totalPrice: LiveData<Double>
        get() = _TotalPrice

    fun onIncrease(cartItem: CartItem) {
            val items = _currentCart.value?.toMutableList()
            val item = items?.firstOrNull { it.storeItem.itemName == cartItem.storeItem.itemName }

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
            val item = items.firstOrNull { cartItem.storeItem.itemName == it.storeItem.itemName }
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
    }

    private fun updatePrices() {
        _subTotal.value = _currentCart.value?.sumOf { it.storeItem.itemPrice * it.quantity }
        _Tax.value = taxAmount * _subTotal.value!!
        _TotalPrice.value = _Tax.value?.let { _subTotal.value?.plus(it) }
    }
}