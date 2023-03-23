package com.example.storeapp.fragments

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.storeapp.data.Datasource
import com.example.storeapp.models.StoreItem
import java.lang.ref.WeakReference

class StoreViewModel : ViewModel(){

    private lateinit var originalItems: List<StoreItem>
    private val _items = MutableLiveData<List<StoreItem>>(emptyList())
    val items: LiveData<List<StoreItem>>
        get() = _items

    fun init(context: Context) {
        originalItems = Datasource.loadStoreItems(context)
        _items.value = originalItems
    }

    fun sortItems(input: String){
        _items.value = originalItems.filter {
            it.itemName.contains(input, ignoreCase = true)
        }
    }

    fun increaseQuantity(item: StoreItem){

    }

    fun decreaseQuantity(item: StoreItem){

    }
}