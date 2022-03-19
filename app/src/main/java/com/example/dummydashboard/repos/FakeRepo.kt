package com.example.dummydashboard.repos

import com.example.dummydashboard.models.Customer
import com.example.dummydashboard.utils.DataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object FakeRepo : DummyDataRepo {
    private var customers = listOf<Customer>()

    override suspend fun getCustomers(): List<Customer> {
        if (customers.isEmpty()) {
            val scope = CoroutineScope(Dispatchers.IO)
            val routine = scope.launch {
                customers = (DataSource().getDataSet())
            }
            routine.join()
        }
        return customers
    }

    override fun deleteCustomer(customer: Customer): List<Customer> {
        customers = customers.filter { it.customerName != customer.customerName }
        return customers
    }

    override fun addToFavorite(customer: Customer): List<Customer> {
        customers = customers.map {
            if (it.customerName == customer.customerName) {
                it.copy(favorite = true)
            } else {
                it
            }
        }
        return customers
    }

    override fun removeFromFavorite(customer: Customer): List<Customer> {
        customers = customers.map {
            if (it.customerName == customer.customerName) {
                it.copy(favorite = false)
            } else {
                it
            }
        }
        return customers
    }

    override fun addCustomer(customer: Customer): List<Customer> {
        customers = customers + customer
        return customers
    }
}