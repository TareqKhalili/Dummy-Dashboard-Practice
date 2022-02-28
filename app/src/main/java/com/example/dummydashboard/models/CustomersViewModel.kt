package com.example.dummydashboard.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dummydashboard.repos.DefaultRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Suppress("UNCHECKED_CAST")
class CustomersViewModel : ViewModel() {
    private val _customers = MutableLiveData<MutableList<Customer>>()
    val customers: LiveData<MutableList<Customer>>
        get() = _customers

    private val dataSet = DefaultRepo()

    init {
        GlobalScope.launch {
            withContext(Dispatchers.Main) {
                _customers.value = dataSet.getCustomers()
            }
        }
    }

    fun deleteCustomer(customer: Customer): Boolean {
        _customers.value = dataSet.deleteCustomer(customer)
        return true
    }

    fun addToFavorite(customer: Customer): Boolean {
        _customers.value = dataSet.addToFavorite(customer)
        return true
    }

    fun removeFromFavorite(customer: Customer): Boolean {
        _customers.value = dataSet.removeFromFavorite(customer)
        return true
    }
}