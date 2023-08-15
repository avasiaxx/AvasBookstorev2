package com.example.storeapp.data.service

import com.example.storeapp.data.models.Account
import com.example.storeapp.data.models.Cart
import com.example.storeapp.data.models.Login
import com.example.storeapp.data.models.Order
import com.example.storeapp.data.models.StoreItem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiInterface {

    @POST("auth")
    fun getAccount(
        @Body body: Login
    ): Response<Account>

    @GET("orders/{accountId}")
    fun getOrders(
        @Path("accountId") path: Int
    ): Response<List<Order>>

    @GET("cart/{accountId}")
    fun getCart(
        @Path("accountId") path: Int
    ): Response<Cart>

    @GET("inventory")
    fun getInventory():Response<List<StoreItem>>
}
