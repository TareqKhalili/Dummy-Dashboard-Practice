package com.example.dummydashboard.models

import androidx.lifecycle.ViewModel
import com.example.dummydashboard.repos.DefaultRepo
import com.example.dummydashboard.utils.DataSource
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CustomersViewModel : ViewModel() {
    private val defaultRepo = DefaultRepo()

    init {
        GlobalScope.launch {
            DefaultRepo.customers.postValue(DataSource().getDataSet())
        }
    }

    fun deleteCustomer(customer: Customer): Boolean {
        defaultRepo.deleteCustomer(customer)
        return true
    }

    fun addToFavorite(customer: Customer): Boolean {
        defaultRepo.addToFavorite(customer)
        return true
    }
}