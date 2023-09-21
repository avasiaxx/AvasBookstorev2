package com.example.storeapp.domain.repositories

import com.example.storeapp.data.models.StoreItem

interface InventoryRepository {
    fun loadInventory(): List <StoreItem>
}