package com.example.storeapp.account

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class AccountItem(
    val title: String,
    @DrawableRes
    val imageResourceId: Int,
    val itemDescription: String
) {
    companion object {
        fun getOptions(
            context: Context,
            @StringRes titleId: Int,
            @DrawableRes imageResourceId: Int,
            @StringRes itemDescriptionId: Int
        ) = AccountItem(
            title = context.getString(titleId),
            imageResourceId = imageResourceId,
            itemDescription = context.getString(itemDescriptionId)
        )
    }
}