package com.example.dummydashboard.repos

import com.example.dummydashboard.models.Customer
import com.example.dummydashboard.utils.DataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DefaultRepo : DummyDataRepo {
    companion object {
        private var customers = mutableListOf<Customer>()
    }

    override suspend fun getCustomers(): MutableList<Customer> {
        if (customers.isEmpty()) {
            val scope = CoroutineScope(Dispatchers.IO)
            val routine = scope.launch {
                customers.addAll(DataSource().getDataSet())
            }
            routine.join()
        }
        return customers
    }

    override fun deleteCustomer(customer: Customer): MutableList<Customer> {
        customers.remove(customer)
        return customers
    }

    override fun addToFavorite(customer: Customer): MutableList<Customer> {
        customer.favorite = true
        return customers
    }

    override fun removeFromFavorite(customer: Customer): MutableList<Customer> {
        customer.favorite = false
        return customers
    }
}