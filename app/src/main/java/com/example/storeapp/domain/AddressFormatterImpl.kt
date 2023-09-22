package com.example.storeapp.domain

import com.example.storeapp.data.testdata.User
import javax.inject.Inject

class AddressFormatterImpl @Inject constructor(): AddressFormatter {
    override fun formatAddress(user: User): String {
        return "${user.address.streetName} \n " +
                "${user.address.city}, " +
                "${user.address.province} " +
                user.address.postalCode
    }
}