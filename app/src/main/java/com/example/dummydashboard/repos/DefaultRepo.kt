package com.example.dummydashboard.repos

import androidx.lifecycle.MutableLiveData
import com.example.dummydashboard.models.Customer
import com.example.dummydashboard.utils.DataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DefaultRepo : DummyDataRepo {
    companion object {
        val customers = MutableLiveData<MutableList<Customer>>()
        val favoriteCustomers = MutableLiveData<MutableList<Customer>>()
    }

    override suspend fun getCustomers() {
        val scope = CoroutineScope(Dispatchers.IO)
        scope.launch {
            customers.postValue(DataSource().getDataSet())
        }
    }

    override fun deleteCustomer(customer: Customer) {
        customers.value = customers.value?.filter {
            it.customerName != customer.customerName
        } as MutableList<Customer>
        removeFromFavorite(customer)
    }

    override fun addToFavorite(customer: Customer) {
        customer.favorite = true
        favoriteCustomers.value = customers.value?.filter {
            it.favorite
        } as MutableList<Customer>
        println(favoriteCustomers.value)
    }

    override fun removeFromFavorite(customer: Customer) {
        customer.favorite = false
        favoriteCustomers.value = customers.value?.filter {
            it.favorite
        } as MutableList<Customer>
    }
}