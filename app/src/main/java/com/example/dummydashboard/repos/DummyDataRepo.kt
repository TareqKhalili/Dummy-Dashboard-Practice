package com.example.dummydashboard.repos

import com.example.dummydashboard.models.Customer

interface DummyDataRepo {
    suspend fun getCustomers()
    fun deleteCustomer(customer: Customer)
    fun addToFavorite(customer: Customer)
    fun removeFromFavorite(customer: Customer)
}