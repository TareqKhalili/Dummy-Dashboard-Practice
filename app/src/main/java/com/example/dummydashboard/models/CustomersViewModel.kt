package com.example.dummydashboard.models

import androidx.lifecycle.*
import com.example.dummydashboard.repos.FakeRepo
import kotlinx.coroutines.launch

class CustomersViewModel : ViewModel() {

    private val _customers = MutableLiveData<List<Customer>>()
    val customers: LiveData<List<Customer>>
        get() = _customers


    init {
        viewModelScope.launch {
            _customers.value = FakeRepo.getCustomers()
        }
    }


    fun deleteCustomer(customer: Customer): Boolean {
        _customers.value = FakeRepo.deleteCustomer(customer)
        return true
    }


    fun addToFavorite(customer: Customer): Boolean {
        _customers.value = FakeRepo.addToFavorite(customer)
        return true
    }


    fun removeFromFavorite(customer: Customer): Boolean {
        _customers.value = FakeRepo.removeFromFavorite(customer)
        return true
    }
}