package com.example.storeapp.domain.repositories

import com.example.storeapp.data.models.PaymentInfo

interface PaymentInfoRepository {

    fun loadPaymentInfo(): List<PaymentInfo>
}