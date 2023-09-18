package com.example.storeapp.checkout.paymentmethod

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.storeapp.data.Datasource
import com.example.storeapp.data.models.PaymentInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
//Map a Display model for the selected item
@HiltViewModel
class PaymentMethodViewModel @Inject constructor(
    var datasource: Datasource
): ViewModel() {
    companion object {
        private const val MAX_CHARACTER_COUNT = 12
    }
    private lateinit var paymentMethods: List<PaymentInfo>
    private val _currentPaymentMethods = MutableLiveData<List<PaymentInfo>?>(emptyList())


    val items: LiveData<List<PaymentInfo>?>
        get() = _currentPaymentMethods

    fun init(){
        paymentMethods = datasource.loadPaymentInfo()
        convertToAsterisks()
        _currentPaymentMethods.value = paymentMethods
    }

    private fun convertToAsterisks(){
        for(paymentMethod in paymentMethods){
            val ccNumber = paymentMethod.card
            val replaced: String = ccNumber.replaceRange(0, MAX_CHARACTER_COUNT,
                "*".repeat(MAX_CHARACTER_COUNT))
            paymentMethod.card = replaced
        }
    }

    fun deletePosition(position: Int){
        val current = _currentPaymentMethods.value?.toMutableList()
        current?.removeAt(position)
        _currentPaymentMethods.value = current
    }

    fun addNewPaymentMethod(id: Int, card: String, expiry: String, ccv: Int){
        val newPaymentOption = PaymentInfo(id, card, expiry, ccv)
        val current = _currentPaymentMethods.value?.toMutableList()
        current?.add(newPaymentOption)
        paymentMethods = current?.toList()!!
        convertToAsterisks()
        _currentPaymentMethods.value = paymentMethods
    }
}