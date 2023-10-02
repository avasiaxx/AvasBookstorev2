package com.example.storeapp.data

import android.content.Context
import com.example.storeapp.R
import com.example.storeapp.data.models.Account
import com.example.storeapp.data.models.Address
import com.example.storeapp.data.models.Cart
import com.example.storeapp.data.models.Login
import com.example.storeapp.data.models.Order
import com.example.storeapp.data.models.PaymentInfo
import com.example.storeapp.data.models.Person
import com.example.storeapp.data.models.StoreItem
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class TestDatasource @Inject constructor(
    @ApplicationContext private val context: Context
) : Datasource {


    private val person: Person = Person(
        1,
        "Markus Clarence",
        "markusclarence34@gmail.com",
        "123-234-1234"
    )

    private val address: Address = Address(
        1,
        "439 Port Washington Road.",
        "Milk River",
        "AL",
        "T0K 1M0",
        "Canada"
    )

    private val paymentMethods: List<PaymentInfo> = listOf(
        PaymentInfo(
            1,
            context.getString(R.string.credit_card_number),
            context.getString(R.string.exp_date),
            context.getString(R.string.ccv).toInt()
        ),
        PaymentInfo(
            2,
            context.getString(R.string.credit_card_number2),
            context.getString(R.string.exp_date2),
            context.getString(R.string.ccv2).toInt()
        ),
    )

    private val account: Account = Account(
        1,
        person,
        address,
        paymentMethods.first(),
        "admin",
        isCustomer = true,
        isManager = true
    )

    private val login: Login = Login(
        account.userName,
        "123456"
    )

    private val inventory: List<StoreItem> = listOf(
        StoreItem(
            1,
            context.getString(R.string.item_1_name),
            context.getString(R.string.item_description_1),
            1.99
        ),
        StoreItem(
            2,
            context.getString(R.string.item_2_name),
            context.getString(R.string.item_description_1),
            1.99
        ),
        StoreItem(
            3,
            context.getString(R.string.item_3_name),
            context.getString(R.string.item_description_1),
            1.99
        ),
        StoreItem(
            4,
            context.getString(R.string.item_4_name),
            context.getString(R.string.item_description_1),
            1.99
        ),
        StoreItem(
            5,
            context.getString(R.string.item_5_name),
            context.getString(R.string.item_description_1),
            1.99
        ),
        StoreItem(
            6,
            context.getString(R.string.item_6_name),
            context.getString(R.string.item_description_1),
            1.99
        )
    )

    override fun loadItems(): List<StoreItem> {
        return inventory
    }

    //Load Test Data for User Payment Info
    override fun loadPaymentInfo(): List<PaymentInfo> {
        return paymentMethods
    }

    override fun loadTestLogin(): Login {
        return login
    }

    override fun getOrder(): Order {
        TODO("Not yet implemented")
    }

    override fun login(login: Login?): Boolean {
        return login == this.login
    }

    override fun logout() {
        // TODO: revoke authentication
    }

    override fun loadAccount(login: Login): Account {
        return account
    }

    override fun loadAddress(): Address {
        return address
    }

    override fun loadPerson(): Person {
        return person
    }

    override fun loadCart(): Cart {
        TODO("Not yet implemented")
    }

    override fun loadOrders(): List<Order> {
        TODO("Not yet implemented")
    }
}