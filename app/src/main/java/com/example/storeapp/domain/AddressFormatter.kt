package com.example.storeapp.domain

import com.example.storeapp.data.models.Address


interface AddressFormatter {
    fun formatAddress(address: Address): String
}