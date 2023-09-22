package com.example.storeapp.checkout

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.storeapp.data.models.CartItem
import com.example.storeapp.data.models.Order
import com.example.storeapp.data.testdata.User
import com.example.storeapp.domain.CCAsterisksFormatter
import com.example.storeapp.domain.CurrencyFormatter
import com.example.storeapp.domain.AddressFormatter
import com.example.storeapp.domain.repositories.OrderRepository
import com.example.storeapp.domain.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CheckOutViewModel @Inject constructor(
    private var orderRepository: OrderRepository,
    private var currencyFormatter: CurrencyFormatter,
    userRepository: UserRepository,
    private var ccAsterisksFormatter: CCAsterisksFormatter,
    private var addressFormatter: AddressFormatter
): ViewModel(){

    private lateinit var order: Order

    private lateinit var cartItems: List<CartItem>
    private val _currentCartItems = MutableLiveData<List<CartItem>>(emptyList())

    var user:User = userRepository.getUser()

    val items: LiveData<List<CartItem>> get() = _currentCartItems

    fun init(){
        order = orderRepository.loadOrder()
        cartItems = order.cart.items
        _currentCartItems.value = cartItems
    }
    fun getFormattedSubTotal(): String{
        return currencyFormatter.formatCurrency(order.subTotal)
    }

    fun getFormattedShippingCost(): String{
        return currencyFormatter.formatCurrency(SHIPPING_COST)
    }

    fun getFormattedTax(): String{
        return currencyFormatter.formatCurrency(order.taxAmount)
    }

    fun getFormattedTotal(): String{
        val newTotal = order.total + SHIPPING_COST
        return currencyFormatter.formatCurrency(newTotal)
    }

    fun formatUserCC(): String{
        return ccAsterisksFormatter.convertToAsterisks(user.account.primaryPaymentInfo)
    }

    fun formatShippingAddress(): String{
        return addressFormatter.formatAddress(user)
    }
    companion object {
        private const val SHIPPING_COST = 2.99
    }
}