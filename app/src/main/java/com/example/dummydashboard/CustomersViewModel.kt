package com.example.dummydashboard

import com.example.dummydashboard.models.Customer

class CustomersViewModel {
    companion object {
        var customersList: List<Customer> = DataSource.getDataSet()
        var favoriteCustomersList: List<Customer> = ArrayList<Customer>()
    }
}