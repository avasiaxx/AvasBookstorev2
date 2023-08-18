package com.example.storeapp.domain.repositories

import com.example.storeapp.data.Datasource
import com.example.storeapp.data.models.StoreItem
import com.example.storeapp.databinding.FragmentStoreListBinding
import javax.inject.Inject

class InventoryRepositoryImpl @Inject constructor(
    private val datasource: Datasource
) : InventoryRepository {
    override fun loadInventory(binding: FragmentStoreListBinding): List<StoreItem> {
        return datasource.loadItems(binding)
    }
}