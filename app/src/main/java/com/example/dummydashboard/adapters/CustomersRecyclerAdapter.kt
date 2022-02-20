package com.example.dummydashboard.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.dummydashboard.R
import com.example.dummydashboard.databinding.CustomerListItemBinding
import com.example.dummydashboard.models.Customer
import com.example.dummydashboard.models.CustomersViewModel
import com.example.dummydashboard.utils.CustomerDiffItemCallback
import com.example.dummydashboard.utils.setupIcons


class CustomersRecyclerAdapter(private val viewModel: CustomersViewModel) :
    ListAdapter<Customer, CustomersRecyclerAdapter.CustomersViewHolder>(CustomerDiffItemCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomersViewHolder {
        return CustomersViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.customer_list_item,
            parent,
            false), viewModel)
    }

    override fun onBindViewHolder(holder: CustomersViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class CustomersViewHolder(
        itemView: CustomerListItemBinding,
        private val viewModel: CustomersViewModel,
    ) : RecyclerView.ViewHolder(itemView.root) {

        private val customerName = itemView.customerName
        private val customerImage = itemView.customerImage

        private val requestOptions = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)


        fun bind(customer: Customer) {
            customerName.text = customer.customerName

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(customer.image)
                .into(customerImage)

            itemView.setOnLongClickListener {
                try {
                    handlePopUpMenu(it, customer)
                    true
                } catch (error: Exception) {
                    println(error.printStackTrace())
                    false
                }
            }

        }


        private fun handlePopUpMenu(view: View, customer: Customer) {
            val popupMenu = PopupMenu(itemView.context, view)

            popupMenu.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.add_to_favorite -> viewModel.addToFavorite(customer)

                    R.id.delete -> viewModel.deleteCustomer(customer)

                    else -> false
                }
            }

            popupMenu.inflate(R.menu.customers_menu_items)

            popupMenu.setupIcons()
        }
    }
}

