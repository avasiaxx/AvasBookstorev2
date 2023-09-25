package com.example.storeapp.data.testdata

import com.example.storeapp.data.Datasource
import com.example.storeapp.data.models.Account
import com.example.storeapp.data.models.Address
import com.example.storeapp.data.models.Login
import com.example.storeapp.data.models.PaymentInfo
import com.example.storeapp.data.models.Person
import javax.inject.Inject

class User @Inject constructor(
    val datasource: Datasource
){
    private val paymentInfo: List<PaymentInfo> = datasource.loadPaymentInfo()

    private val person: Person = Person(
        1,
        "Markus Clarence",
        "markusclarence34@gmail.com",
        "123-234-1234")


    val address: Address = Address(
        1,
        "439 Port Washington Road.",
        "Milk River",
        "AL",
        "T0K 1M0",
        "Canada"

    )
    val account: Account = Account(
        1,
        person,
        address,
        paymentInfo.first(),
        "admin",
        isCustomer = true,
        isManager = true
    )

    val login: Login = Login(
        account.userName,
        "123"
    )
}