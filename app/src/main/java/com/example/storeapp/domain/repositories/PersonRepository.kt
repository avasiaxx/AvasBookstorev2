package com.example.storeapp.domain.repositories

import com.example.storeapp.data.models.Person

interface PersonRepository {

    fun loadPerson(): Person
}