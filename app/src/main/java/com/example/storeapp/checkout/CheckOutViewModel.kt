package com.example.storeapp.checkout

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.storeapp.R
import com.example.storeapp.data.Datasource
import com.example.storeapp.data.models.PaymentInfo
import com.example.storeapp.databinding.PaymentItemBinding
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
//Map a Display model for the selected item
@HiltViewModel
class CheckOutViewModel @Inject constructor(
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
            val replaced: String = ccNumber.replaceRange(0, Companion.MAX_CHARACTER_COUNT,
                "*".repeat(MAX_CHARACTER_COUNT))
            paymentMethod.card = replaced
        }
    }
}