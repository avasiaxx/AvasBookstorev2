package com.example.storeapp.domain.repositories


import com.example.storeapp.data.Datasource
import com.example.storeapp.data.models.Address
import javax.inject.Inject

class AddressRepositoryImpl @Inject constructor(
    val datasource: Datasource
) : AddressRepository {
    override fun loadAddress(): Address {
        return datasource.loadAddress()
    }
}