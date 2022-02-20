package com.example.dummydashboard.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.dummydashboard.models.Customer

class CustomerDiffItemCallback : DiffUtil.ItemCallback<Customer>() {
    override fun areItemsTheSame(oldItem: Customer, newItem: Customer) =
        oldItem.customerName == newItem.customerName

    override fun areContentsTheSame(oldItem: Customer, newItem: Customer) = oldItem == newItem

}