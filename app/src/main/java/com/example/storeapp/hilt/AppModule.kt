package com.example.storeapp.hilt

import com.example.storeapp.data.Datasource
import com.example.storeapp.data.TestDatasource
import com.example.storeapp.data.service.ApiInterface
import com.example.storeapp.domain.AddressFormatter
import com.example.storeapp.domain.CCAsterisksFormatter
import com.example.storeapp.domain.CCAsterisksFormatterImpl
import com.example.storeapp.domain.CurrencyFormatter
import com.example.storeapp.domain.CurrencyFormatterImpl
import com.example.storeapp.domain.AddressFormatterImpl
import com.example.storeapp.domain.Validator
import com.example.storeapp.domain.ValidatorImpl
import com.example.storeapp.domain.repositories.AccountRepository
import com.example.storeapp.domain.repositories.AccountRepositoryImpl
import com.example.storeapp.domain.repositories.AddressRepository
import com.example.storeapp.domain.repositories.AddressRepositoryImpl
import com.example.storeapp.domain.repositories.CartRepository
import com.example.storeapp.domain.repositories.CartRepositoryImpl
import com.example.storeapp.domain.repositories.InventoryRepository
import com.example.storeapp.domain.repositories.InventoryRepositoryImpl
import com.example.storeapp.domain.repositories.OrderRepository
import com.example.storeapp.domain.repositories.OrderRepositoryImpl
import com.example.storeapp.domain.repositories.PaymentInfoRepository
import com.example.storeapp.domain.repositories.PaymentInfoRepositoryImpl
import com.example.storeapp.domain.repositories.PersonRepository
import com.example.storeapp.domain.repositories.PersonRepositoryImpl
import com.example.storeapp.domain.repositories.LoginRepository
import com.example.storeapp.domain.repositories.LoginRepositoryImpl
import com.google.gson.GsonBuilder
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Suppress("unused")
@Module
@InstallIn(SingletonComponent::class)
interface AppModule {

    companion object {
        private const val BASEURL = "https://ovd.app:5796"

        @Provides
        fun provideApiInterface(): ApiInterface {
            val gson = GsonBuilder()
                .setLenient()
                .create()
            val okHttpClient = OkHttpClient.Builder()
                .readTimeout(100, TimeUnit.SECONDS)
                .connectTimeout(100, TimeUnit.SECONDS)
                .build()
            return Retrofit.Builder()
                .baseUrl(BASEURL)
                .client(okHttpClient)
                .addConverterFactory((GsonConverterFactory.create(gson)))
                .build()
                .create(ApiInterface::class.java)
        }
    }

    @Binds
    fun bindValidator(validatorImpl: ValidatorImpl): Validator
    @Binds
    fun bindAddressFormatter(addressFormatterImpl: AddressFormatterImpl): AddressFormatter

    @Binds
    fun bindCurrencyFormatter(currencyFormatterImpl: CurrencyFormatterImpl): CurrencyFormatter

    @Binds
    fun bindCCAsterisksFormatter(ccAsterisksFormatterImpl: CCAsterisksFormatterImpl): CCAsterisksFormatter

    @Singleton
    @Binds
    fun bindDatasource(testDatasource: TestDatasource): Datasource

    @Singleton
    @Binds
    fun bindInventoryRepository(inventoryRepositoryImpl: InventoryRepositoryImpl): InventoryRepository

    @Singleton
    @Binds
    fun bindCartRepository(cartRepositoryImpl: CartRepositoryImpl): CartRepository

    @Singleton
    @Binds
    fun bindOrderRepository(orderRepositoryImpl: OrderRepositoryImpl): OrderRepository

    @Singleton
    @Binds
    fun bindAccountRepository(accountRepositoryImpl: AccountRepositoryImpl): AccountRepository

    @Singleton
    @Binds
    fun bindPersonRepository(personRepositoryImpl: PersonRepositoryImpl): PersonRepository

    @Singleton
    @Binds
    fun bindAddressRepository(addressRepositoryImpl: AddressRepositoryImpl): AddressRepository

    @Singleton
    @Binds
    fun bindPaymentInfoRepository(paymentInfoRepositoryImpl: PaymentInfoRepositoryImpl): PaymentInfoRepository

    @Singleton
    @Binds
    fun bindLoginRepository(loginRepositoryImpl: LoginRepositoryImpl): LoginRepository

}