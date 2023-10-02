package com.example.storeapp.domain.repositories

import com.example.storeapp.data.Datasource
import com.example.storeapp.data.models.StoreItem
import javax.inject.Inject

class InventoryRepositoryImpl @Inject constructor(
    private val datasource: Datasource
) : InventoryRepository {

    private var inventory: MutableList<StoreItem> = mutableListOf()
    override fun loadInventory(): List<StoreItem> {
        if (inventory.isEmpty()) {
            inventory.addAll(datasource.loadItems())
        }
        return inventory
    }
}