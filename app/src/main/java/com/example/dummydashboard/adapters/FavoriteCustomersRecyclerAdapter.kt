package com.example.dummydashboard.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.dummydashboard.R
import com.example.dummydashboard.databinding.CustomerItemBinding
import com.example.dummydashboard.models.Customer
import com.example.dummydashboard.models.CustomersViewModel
import com.example.dummydashboard.utils.setupIcons

class FavoriteCustomersRecyclerAdapter :
    RecyclerView.Adapter<FavoriteCustomersRecyclerAdapter.FavoriteCustomersViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteCustomersViewHolder {
        return FavoriteCustomersViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.customer_item,
            parent,
            false))
    }

    override fun onBindViewHolder(holder: FavoriteCustomersViewHolder, position: Int) {
        holder.bind(CustomersViewModel.favoriteCustomersList[position])
    }

    override fun getItemCount(): Int {
        return CustomersViewModel.favoriteCustomersList.size
    }


    fun submitList(customersList: List<Customer>) {
        CustomersViewModel.favoriteCustomersList = customersList
    }


    open class FavoriteCustomersViewHolder(
        itemView: CustomerItemBinding,
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
                    R.id.remove_from_favorite -> removeFromFavorite(customer)

                    R.id.delete -> deleteCustomer(customer)

                    else -> false
                }
            }

            popupMenu.inflate(R.menu.favorite_menu_items)

            popupMenu.setupIcons()
        }


        private fun removeFromFavorite(customer: Customer): Boolean {
            customer.favorite = false
            val index = CustomersViewModel.favoriteCustomersList.indexOf(customer)
            (CustomersViewModel.favoriteCustomersList as ArrayList).removeAt(index)
            bindingAdapter?.notifyItemRemoved(index)
            return true
        }


        private fun deleteCustomer(customer: Customer): Boolean {
            var index = CustomersViewModel.customersList.indexOf(customer)

            return try {
                (CustomersViewModel.customersList as ArrayList).removeAt(index)
                index = CustomersViewModel.favoriteCustomersList.indexOf(customer)
                (CustomersViewModel.favoriteCustomersList as ArrayList).removeAt(index)
                bindingAdapter?.notifyItemRemoved(index)
                true
            } catch (error: Exception) {
                Toast.makeText(itemView.context,
                    "Failed to delete",
                    Toast.LENGTH_SHORT).show()
                false
            }
        }

    }
}