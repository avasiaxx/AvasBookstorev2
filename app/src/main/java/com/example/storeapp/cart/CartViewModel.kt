package com.example.storeapp.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.storeapp.store.StoreItem

class CartViewModel: ViewModel() {

    private lateinit var originalCart: List<CartItem>
    private val _currentCart = MutableLiveData<List<CartItem>?>(emptyList())
    val items: MutableLiveData<List<CartItem>?>
        get() = _currentCart

    fun onIncrease(storeItem: StoreItem): Boolean {
            val items = _currentCart.value?.toMutableList()
            val item = items?.firstOrNull { it.storeItem.itemName == storeItem.itemName }

            if (item == null) {
                val cartItem: CartItem = CartItem(storeItem, 1)
                items?.add(cartItem)
                _currentCart.value = items
                return false
            }
        return true
    }
//
//    fun onDecrease(storeItem: StoreItem){
//        val items = _items.value?.toMutableList() ?: return
//        val item = items.firstOrNull {it.itemName == storeItem.itemName} ?: return
//        if(item.itemQuantity > 0) {
//            val index = items.indexOf(item)
//            items.remove(item)
//            items.add(
//                index,
//                item.copy(itemQuantity = item.itemQuantity.minus((1)))
//            )
//        }
//        _items.value = items
//    }

    fun removeFromCart(cartItem: CartItem){
        val items = _currentCart.value?.toMutableList() ?: return
        items.remove(cartItem)
        _currentCart.value = items
    }
}