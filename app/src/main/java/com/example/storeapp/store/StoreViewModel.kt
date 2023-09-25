package com.example.storeapp.store

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.storeapp.data.models.StoreItem
import com.example.storeapp.domain.repositories.InventoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StoreViewModel @Inject constructor(
    private var inventoryRepository: InventoryRepository,
) : ViewModel(){

    private lateinit var originalItems: List<StoreItem>
    private val _items = MutableLiveData<List<StoreItem>>(emptyList())
    val items: LiveData<List<StoreItem>>
        get() = _items

    fun init() {
        originalItems = inventoryRepository.loadInventory()
        _items.value = originalItems
    }

    fun sortItems(input: String){
        _items.value = originalItems.filter {
            it.name.contains(input, ignoreCase = true)
        }
    }
}