package com.example.dummydashboard.models

import androidx.lifecycle.ViewModel
import com.example.dummydashboard.repos.DefaultRepo

class FavoriteCustomersViewModel : ViewModel() {
    private val defaultRepo = DefaultRepo()

    fun deleteCustomer(customer: Customer): Boolean {
        defaultRepo.deleteCustomer(customer)
        return true
    }

    fun removeFromFavorite(customer: Customer): Boolean {
        defaultRepo.removeFromFavorite(customer)
        return true
    }
}