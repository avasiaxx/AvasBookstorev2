package com.example.storeapp.checkout

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.storeapp.data.Datasource
import com.example.storeapp.data.models.PaymentInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
//Map a Display model for the selected item
@HiltViewModel
class CheckOutViewModel @Inject constructor(
    var datasource: Datasource
): ViewModel() {

    private lateinit var paymentMethods: List<PaymentInfo>
    private val _currentPaymentMethods = MutableLiveData<List<PaymentInfo>?>(emptyList())

    val items: LiveData<List<PaymentInfo>?>
        get() = _currentPaymentMethods

    fun init(){
        paymentMethods = datasource.loadPaymentInfo()
        _currentPaymentMethods.value = paymentMethods
    }
}