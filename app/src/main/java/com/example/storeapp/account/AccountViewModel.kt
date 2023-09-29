package com.example.storeapp.account

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
): ViewModel() {

    private val _items = MutableLiveData<List<AccountItem>>(emptyList())
    val items: LiveData<List<AccountItem>>
        get() = _items

        fun init(context: Context) {
           //_items.value = testDatasource.loadSettingOptions()
        }
}