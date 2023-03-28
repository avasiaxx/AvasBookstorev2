package com.example.storeapp.store

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.storeapp.data.Datasource

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

    fun onIncrease(storeItem: StoreItem) {
        //Get a copy of the current data list, get the item, get the index, remove the previous item,
        //update the new item then place it back into the live data list
        val items = _items.value?.toMutableList() ?: return
        val item = items.firstOrNull { it.itemName == storeItem.itemName } ?: return
        val index = items.indexOf(item)
        items.remove(item)
        items.add(
            index,
            item.copy( itemQuantity = item.itemQuantity.plus(1))
        )
        _items.value = items
    }

    fun onDecrease(storeItem: StoreItem){
        val items = _items.value?.toMutableList() ?: return
        val item = items.firstOrNull {it.itemName == storeItem.itemName} ?: return
        if(item.itemQuantity > 0) {
            val index = items.indexOf(item)
            items.remove(item)
            items.add(
                index,
                item.copy(itemQuantity = item.itemQuantity.minus((1)))
            )
        }
        _items.value = items
    }
}