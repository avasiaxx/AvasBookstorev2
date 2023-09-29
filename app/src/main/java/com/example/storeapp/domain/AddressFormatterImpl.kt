package com.example.storeapp.domain


import com.example.storeapp.data.models.Address
import javax.inject.Inject

class AddressFormatterImpl @Inject constructor(): AddressFormatter {
    override fun formatAddress(address: Address): String {
        return "${address.streetName} \n " +
                "${address.city}, " +
                "${address.province} " +
                address.postalCode
    }
}