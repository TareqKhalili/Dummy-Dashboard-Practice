package com.example.dummydashboard.models

import com.example.dummydashboard.utils.DataSource

class CustomersViewModel {

    companion object {
        var customersList: List<Customer> = DataSource.getDataSet()
        var favoriteCustomersList: List<Customer> = ArrayList()
    }
}