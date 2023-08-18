package com.example.storeapp.account

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.storeapp.data.TestDatasource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private var testDatasource: TestDatasource
): ViewModel() {

    private val _items = MutableLiveData<List<AccountItem>>(emptyList())
    val items: LiveData<List<AccountItem>>
        get() = _items

        fun init(context: Context) {
           _items.value = testDatasource.loadSettingOptions(context)
        }
}