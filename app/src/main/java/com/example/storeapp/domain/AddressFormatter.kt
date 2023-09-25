package com.example.storeapp.domain

import com.example.storeapp.data.testdata.User

interface AddressFormatter {

    fun formatAddress(user: User): String
}