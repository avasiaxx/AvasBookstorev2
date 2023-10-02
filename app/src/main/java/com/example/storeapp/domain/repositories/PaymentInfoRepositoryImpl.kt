package com.example.storeapp.domain.repositories

import com.example.storeapp.data.Datasource
import com.example.storeapp.data.models.PaymentInfo
import javax.inject.Inject

class PaymentInfoRepositoryImpl @Inject constructor(
    private val datasource: Datasource
) : PaymentInfoRepository {
    override fun loadPaymentInfo(): List<PaymentInfo> {
        return datasource.loadPaymentInfo()
    }
}