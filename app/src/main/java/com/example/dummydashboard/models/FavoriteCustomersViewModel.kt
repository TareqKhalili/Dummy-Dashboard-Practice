package com.example.dummydashboard.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dummydashboard.repos.DefaultRepo

class FavoriteCustomersViewModel(private val dataSet: DefaultRepo) : ViewModel() {

    private val _favoriteCustomers = MutableLiveData<MutableList<Customer>>()
    val favoriteCustomers: LiveData<MutableList<Customer>>
        get() = _favoriteCustomers

    init {
        _favoriteCustomers.postValue(dataSet.getFavoriteCustomers())
    }

    fun deleteCustomer(customer: Customer): Boolean {
        _favoriteCustomers.postValue(dataSet.deleteCustomer(customer))
        return true
    }

    fun removeFromFavorite(customer: Customer): Boolean {
        _favoriteCustomers.postValue(dataSet.removeFromFavorite(customer))
        return true
    }
}