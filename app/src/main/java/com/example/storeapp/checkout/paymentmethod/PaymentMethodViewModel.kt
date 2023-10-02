package com.example.storeapp.checkout.paymentmethod

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.storeapp.data.models.PaymentInfo
import com.example.storeapp.domain.CCAsterisksFormatter
import com.example.storeapp.domain.repositories.AccountRepository
import com.example.storeapp.domain.repositories.PaymentInfoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

//Map a Display model for the selected item
@HiltViewModel
class PaymentMethodViewModel @Inject constructor(
    private var paymentInfoRepository: PaymentInfoRepository,
    private var ccAsterisksFormatter: CCAsterisksFormatter,
    private var accountRepository: AccountRepository
) : ViewModel() {

    private lateinit var paymentMethods: List<PaymentInfo>
    private val _currentPaymentMethods = MutableLiveData<List<PaymentInfo>?>(emptyList())

    val items: LiveData<List<PaymentInfo>?>
        get() = _currentPaymentMethods

    private lateinit var selectedPaymentMethod: PaymentInfo

    fun init() {
        paymentMethods = paymentInfoRepository.loadPaymentInfo()
        convertToAsterisks()
        _currentPaymentMethods.value = paymentMethods
    }

    private fun convertToAsterisks() {
        for (paymentMethod in paymentMethods) {
            paymentMethod.card = ccAsterisksFormatter.convertToAsterisks(paymentMethod)
        }
    }

    fun deletePosition(position: Int) {
        val current = _currentPaymentMethods.value?.toMutableList()
        current?.removeAt(position)
        _currentPaymentMethods.value = current
    }

    fun addNewPaymentMethod(id: Int, card: String, expiry: String, ccv: Int) {
        val newPaymentOption = PaymentInfo(id, card, expiry, ccv)
        val current = _currentPaymentMethods.value?.toMutableList()
        current?.add(newPaymentOption)
        paymentMethods = current?.toList()!!
        convertToAsterisks()
        _currentPaymentMethods.value = paymentMethods
    }

    fun onPaymentMethodSelected(paymentInfo: PaymentInfo) {
        selectedPaymentMethod = paymentInfo
    }

    fun savePaymentMethod() {
        for (paymentMethods in paymentMethods) {
            if (paymentMethods.id == selectedPaymentMethod.id) {
                accountRepository.account.primaryPaymentInfo = selectedPaymentMethod
            }
        }
    }
}