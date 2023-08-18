package com.example.storeapp.data.models

data class StoreItem (
    val id: Int? = null,
    val name: String,
    val description: String,
    val cost: Double
) {
    companion object{
        fun testData(
            id: Int,
            name: String,
            description: String,
            cost: Double
        ) = StoreItem(
            id = id,
            name = name,
            description = description,
            cost = cost
        )
    }
}