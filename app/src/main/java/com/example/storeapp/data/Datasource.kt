package com.example.storeapp.data

import android.content.Context
import com.example.storeapp.store.StoreItem
import com.example.storeapp.R
import com.example.storeapp.account.AccountItem
import com.example.storeapp.cart.CartItem
import com.example.storeapp.orders.OrderItem
import kotlin.random.Random

object Datasource {

    fun loadStoreItems(
        context: Context
    ): List<StoreItem>{
        return listOf(
            StoreItem.testData(context, R.string.item_1_name, R.drawable.item1, R.string.item_description_1, 15.99),
            StoreItem.testData(context, R.string.item_2_name, R.drawable.item2, R.string.item_description_2, 20.99),
            StoreItem.testData(context, R.string.item_3_name, R.drawable.item3, R.string.item_description_3, 11.99),
            StoreItem.testData(context, R.string.item_4_name, R.drawable.item4, R.string.item_description_4, 10.99),
            StoreItem.testData(context, R.string.item_5_name, R.drawable.item5, R.string.item_description_5, 7.99),
            StoreItem.testData(context, R.string.item_6_name, R.drawable.item6, R.string.item_description_6, 8.99),
            StoreItem.testData(context, R.string.item_7_name, R.drawable.item7, R.string.item_description_7, 9.99),
            StoreItem.testData(context, R.string.item_8_name, R.drawable.item8, R.string.item_description_8, 5.99),
            StoreItem.testData(context, R.string.item_9_name, R.drawable.item9, R.string.item_description_9, 4.99),
            StoreItem.testData(context, R.string.item_10_name, R.drawable.item10, R.string.item_description_10, 9.99)
        )
    }

    //TODO Code Clean up - Account Settings
    fun loadSettingOptions(
        context: Context
    ):
        List<AccountItem>{
            return listOf(
                AccountItem.getOptions(context, R.string.login_security, R.drawable.login_security_icon, R.string.login_security_description),
                AccountItem.getOptions(context, R.string.manage_payment_information, R.drawable.payment_icon, R.string.manage_payment_information_description),
                AccountItem.getOptions(context, R.string.your_addresses, R.drawable.address_icon, R.string.your_addresses_description),
                AccountItem.getOptions(context, R.string.help_center, R.drawable.help_center_icon, R.string.help_center_description),
                AccountItem.getOptions(context, R.string.settings, R.drawable.settings_icon, R.string.settings_description),
                AccountItem.getOptions(context, R.string.contact_us, R.drawable.contact_support_icon, R.string.contact_us_description)
            )
    }

    fun loadOrderTestData(
        context: Context,
        cartItem: CartItem,
        total: Double
    ): OrderItem {
        val randOrderNum = Random.nextInt(10000,99999)
        return OrderItem.getOrderData(context, R.string.delivery_date, total,
            R.string.shipping_address, randOrderNum, cartItem)
    }
}