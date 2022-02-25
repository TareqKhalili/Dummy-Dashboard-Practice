package com.example.dummydashboard.repos

import com.example.dummydashboard.models.Customer

interface DummyDataRepo {
    suspend fun getCustomers(): MutableList<Customer>
    fun deleteCustomer(customer: Customer): MutableList<Customer>
    fun addToFavorite(customer: Customer): MutableList<Customer>
    fun removeFromFavorite(customer: Customer): MutableList<Customer>
}