package com.example.storeapp.data

import com.example.storeapp.data.models.Account
import com.example.storeapp.data.models.Address
import com.example.storeapp.data.models.Cart
import com.example.storeapp.data.models.Login
import com.example.storeapp.data.models.Order
import com.example.storeapp.data.models.PaymentInfo
import com.example.storeapp.data.models.Person
import com.example.storeapp.data.models.StoreItem
import com.example.storeapp.data.service.ApiInterface
import javax.inject.Inject


class NetworkDatasource @Inject constructor(
    private val apiInterface: ApiInterface
) : Datasource {
    override fun loadAccount(login: Login): Account {
        val accountResponse = apiInterface.getAccount(login)

        if (accountResponse.isSuccessful) {
            return accountResponse.body()!!
        }
//        } else {
//
//        }
        return accountResponse.body()!!
    }

    override fun loadAddress(): Address {
        TODO("Not yet implemented")
    }

    override fun loadPerson(): Person {
        TODO("Not yet implemented")
    }

    override fun loadCart(): Cart {
        TODO("Not yet implemented")
    }

    override fun loadOrders(): List<Order> {
        TODO("Not yet implemented")
    }

    override fun loadItems(): List<StoreItem> {
        val inventoryResponse = apiInterface.getInventory()
        var storeItems: List<StoreItem> = emptyList()
        if (inventoryResponse.isSuccessful && inventoryResponse.body() != null) {
            storeItems = inventoryResponse.body().orEmpty()
        }
        return storeItems
    }

    override fun loadPaymentInfo(): List<PaymentInfo> {
        TODO("Not yet implemented")
    }

    override fun loadTestLogin(): Login {
        TODO("Not yet implemented")
    }

    override fun getOrder(): Order {
        TODO("Not yet implemented")
    }

    override fun login(login: Login?): Boolean {
        TODO("Not yet implemented")
    }

    override fun logout() {
        TODO("Not yet implemented")
    }
}