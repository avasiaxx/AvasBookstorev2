package com.example.storeapp.store

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.storeapp.data.Datasource
import com.example.storeapp.data.models.StoreItem
import com.example.storeapp.databinding.FragmentStoreListBinding
import com.example.storeapp.domain.repositories.InventoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StoreViewModel @Inject constructor(
    var inventoryRepository: InventoryRepository,
    var datasource: Datasource
) : ViewModel(){

    private lateinit var originalItems: List<StoreItem>
    private val _items = MutableLiveData<List<StoreItem>>(emptyList())
    val items: LiveData<List<StoreItem>>
        get() = _items

    fun init(context: Context, binding: FragmentStoreListBinding) {
        originalItems = datasource.loadItems(binding)
        //originalItems = inventoryRepository.loadInventory(binding, context)
        _items.value = originalItems
    }

    fun sortItems(input: String){
        _items.value = originalItems.filter {
            it.name.contains(input, ignoreCase = true)
        }
    }
}