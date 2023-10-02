package com.example.storeapp.domain.repositories

import com.example.storeapp.data.Datasource
import com.example.storeapp.data.models.Person
import javax.inject.Inject

class PersonRepositoryImpl @Inject constructor(
    private val datasource: Datasource
) : PersonRepository {
    override fun loadPerson(): Person {
        TODO("Not yet implemented")
    }
}