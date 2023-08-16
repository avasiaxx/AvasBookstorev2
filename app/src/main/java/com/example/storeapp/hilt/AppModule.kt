package com.example.storeapp.hilt

import com.example.storeapp.data.service.ApiInterface
import com.example.storeapp.domain.CurrencyFormatter
import com.example.storeapp.domain.CurrencyFormatterImpl
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

@Module
@InstallIn(SingletonComponent::class)
interface AppModule {

    companion object{
        const val BASEURL = "https://ovd.app:5796"
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
    fun bindCurrencyFormatter(currencyFormatterImpl: CurrencyFormatterImpl): CurrencyFormatter
}