package com.example.storeapp.data

import android.content.Context
import com.example.storeapp.R
import com.example.storeapp.account.AccountItem
import com.example.storeapp.data.models.Account
import com.example.storeapp.data.models.Cart
import com.example.storeapp.data.models.CartItem
import com.example.storeapp.data.models.Login
import com.example.storeapp.data.models.Order
import com.example.storeapp.data.models.StoreItem
import com.example.storeapp.databinding.FragmentStoreListBinding
import com.example.storeapp.orders.OrderHeader
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlin.random.Random

class TestDatasource @Inject constructor(
    @ApplicationContext private val context: Context
): Datasource{

    override fun loadItems(binding: FragmentStoreListBinding): List<StoreItem> {
        return listOf(
            StoreItem(1, context.getString(R.string.item_1_name), context.getString(R.string.item_description_1), 1.99 ),
            StoreItem(2, context.getString(R.string.item_2_name), context.getString(R.string.item_description_1), 1.99 ),
            StoreItem(3, context.getString(R.string.item_3_name), context.getString(R.string.item_description_1), 1.99 ),
            StoreItem(4, context.getString(R.string.item_4_name), context.getString(R.string.item_description_1), 1.99 ),
            StoreItem(5, context.getString(R.string.item_5_name), context.getString(R.string.item_description_1), 1.99 ),
            StoreItem(6, context.getString(R.string.item_6_name), context.getString(R.string.item_description_1), 1.99 )
        )
    }


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
    ): OrderHeader {
        val randOrderNum = Random.nextInt(10000,99999)
        return OrderHeader.getOrderData(context, R.string.delivery_date, total,
            R.string.shipping_address, randOrderNum)
    }

    override fun loadAccount(login: Login): Account {
        TODO("Not yet implemented")
    }

    override fun loadCart(): Cart {
        TODO("Not yet implemented")
    }

    override fun loadOrders(): List<Order> {
        TODO("Not yet implemented")
    }
}