package com.example.storeapp.store

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.storeapp.data.TestDatasource

class StoreViewModel : ViewModel(){

    private lateinit var originalItems: List<StoreItem>
    private val _items = MutableLiveData<List<StoreItem>>(emptyList())
    val items: LiveData<List<StoreItem>>
        get() = _items

    fun init(context: Context) {
        originalItems = TestDatasource.loadStoreItems(context)
        _items.value = originalItems
    }

    fun sortItems(input: String){
        _items.value = originalItems.filter {
            it.itemName.contains(input, ignoreCase = true)
        }
    }
}