package com.example.storeapp.data

import android.content.Context
import com.example.storeapp.models.StoreItem
import com.example.storeapp.R
object Datasource {

    fun loadStoreItems(
        context: Context
    ): List<StoreItem>{
        return listOf(
            StoreItem.testData(context, R.string.item_1_name, R.drawable.item1, R.string.item_description_1),
            StoreItem.testData(context, R.string.item_2_name, R.drawable.item2, R.string.item_description_2),
            StoreItem.testData(context, R.string.item_3_name, R.drawable.item3, R.string.item_description_3),
            StoreItem.testData(context, R.string.item_4_name, R.drawable.item4, R.string.item_description_4),
            StoreItem.testData(context, R.string.item_5_name, R.drawable.item5, R.string.item_description_5),
            StoreItem.testData(context, R.string.item_6_name, R.drawable.item6, R.string.item_description_6),
            StoreItem.testData(context, R.string.item_7_name, R.drawable.item7, R.string.item_description_7),
            StoreItem.testData(context, R.string.item_8_name, R.drawable.item8, R.string.item_description_8),
            StoreItem.testData(context, R.string.item_9_name, R.drawable.item9, R.string.item_description_9),
            StoreItem.testData(context, R.string.item_10_name, R.drawable.item10, R.string.item_description_10)
        )
    }
}