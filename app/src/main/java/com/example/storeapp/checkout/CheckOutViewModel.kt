package com.example.storeapp.checkout

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.storeapp.data.models.CartItem
import com.example.storeapp.data.models.Order
import com.example.storeapp.data.models.PaymentInfo
import com.example.storeapp.domain.CCAsterisksFormatter
import com.example.storeapp.domain.CurrencyFormatter
import com.example.storeapp.domain.AddressFormatter
import com.example.storeapp.domain.repositories.AccountRepository
import com.example.storeapp.domain.repositories.LoginRepository
import com.example.storeapp.domain.repositories.OrderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CheckOutViewModel @Inject constructor(
    private var orderRepository: OrderRepository,
    private var currencyFormatter: CurrencyFormatter,
    private var ccAsterisksFormatter: CCAsterisksFormatter,
    private var addressFormatter: AddressFormatter,
    private var accountRepository: AccountRepository,
    loginRepository: LoginRepository
) : ViewModel() {

    private lateinit var order: Order

    private lateinit var cartItems: List<CartItem>
    private val _currentCartItems = MutableLiveData<List<CartItem>>(emptyList())

    val items: LiveData<List<CartItem>> get() = _currentCartItems

    private var _primaryPaymentMethod = MutableLiveData<PaymentInfo>()
    val primaryPaymentMethod: LiveData<PaymentInfo>
        get() = _primaryPaymentMethod

    val isLoggedIn:Boolean = loginRepository.isLoggedIn

    var orderCreated: Boolean = false

    fun init() {
        order = orderRepository.loadOrder()
        cartItems = order.cart.items
        _currentCartItems.value = cartItems
        _primaryPaymentMethod.value = accountRepository.account.primaryPaymentInfo
    }

    fun getFormattedSubTotal(): String {
        return currencyFormatter.formatCurrency(order.subTotal)
    }

    fun getFormattedShippingCost(): String {
        return currencyFormatter.formatCurrency(SHIPPING_COST)
    }

    fun getFormattedTax(): String {
        return currencyFormatter.formatCurrency(order.taxAmount)
    }

    fun getFormattedTotal(): String {
        val newTotal = order.total + SHIPPING_COST
        return currencyFormatter.formatCurrency(newTotal)
    }

    fun formatUserCC(): String {
        return ccAsterisksFormatter.convertToAsterisks(
            accountRepository.account.primaryPaymentInfo
        )
    }

    fun formatShippingAddress(): String {
        return addressFormatter.formatAddress(
            accountRepository.account.primaryAddress
        )
    }

    companion object {
        private const val SHIPPING_COST = 2.99
    }
}