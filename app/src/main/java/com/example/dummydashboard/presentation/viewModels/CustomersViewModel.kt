package com.example.dummydashboard.presentation.viewModels

import androidx.lifecycle.*
import com.example.dummydashboard.models.Customer
import com.example.dummydashboard.repos.FakeRepo
import kotlinx.coroutines.launch

class CustomersViewModel : ViewModel() {

    private val _customers = MutableLiveData<List<Customer>>()
    val customers: LiveData<List<Customer>>
        get() = _customers

    private val _isLoading = MutableLiveData(true)
    val isLoading: LiveData<Boolean>
        get() = _isLoading


    init {
        viewModelScope.launch {
            _customers.value = FakeRepo.getCustomers()
            _isLoading.value = false
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

    fun addCustomer(customer: Customer) {
        _customers.value = FakeRepo.addCustomer(customer)
    }
}