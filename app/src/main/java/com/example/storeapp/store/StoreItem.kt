package com.example.storeapp.store

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class StoreItem(
    val itemName: String,
    @DrawableRes
    val imageResourceId: Int,
    val itemDescription: String,
    var itemQuantity: Int
) {
    companion object{
        fun testData(
            context: Context,
            @StringRes itemNameId: Int,
            @DrawableRes imageResourceId: Int,
            @StringRes itemDescriptionId: Int,
            itemQuantity: Int
        ) = StoreItem(
            itemName = context.getString(itemNameId),
            imageResourceId = imageResourceId,
            itemDescription = context.getString(itemDescriptionId),
            itemQuantity = 0
            )
    }
}