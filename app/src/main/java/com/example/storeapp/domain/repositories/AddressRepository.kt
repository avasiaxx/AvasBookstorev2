package com.example.storeapp.domain.repositories

import com.example.storeapp.data.models.Address


interface AddressRepository {

    fun loadAddress(): Address
}