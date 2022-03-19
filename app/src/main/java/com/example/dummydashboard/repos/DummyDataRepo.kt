package com.example.dummydashboard.repos

import com.example.dummydashboard.models.Customer

interface DummyDataRepo {
    suspend fun getCustomers(): List<Customer>
    fun addCustomer(customer: Customer): List<Customer>
    fun deleteCustomer(customer: Customer): List<Customer>
    fun addToFavorite(customer: Customer): List<Customer>
    fun removeFromFavorite(customer: Customer): List<Customer>
}