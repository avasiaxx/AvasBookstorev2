package com.example.storeapp.domain.repositories

import com.example.storeapp.data.models.StoreItem
import com.example.storeapp.databinding.FragmentStoreListBinding

interface InventoryRepository {
    fun loadInventory(binding: FragmentStoreListBinding): List <StoreItem>
}