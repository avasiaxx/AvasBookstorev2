package com.example.storeapp.domain.repositories

import com.example.storeapp.data.NetworkDatasource
import com.example.storeapp.databinding.FragmentStoreItemBinding
import javax.inject.Inject

class InventoryRepository {
    @Inject
    lateinit var networkDatasource: NetworkDatasource

    fun loadInventory(binding: FragmentStoreItemBinding){
        var storeItems = networkDatasource.loadItems(binding)


    }
}