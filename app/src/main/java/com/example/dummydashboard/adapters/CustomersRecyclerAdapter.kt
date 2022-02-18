package com.example.dummydashboard.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.dummydashboard.R
import com.example.dummydashboard.models.Customer
import com.example.dummydashboard.models.CustomersViewModel


open class CustomersRecyclerAdapter :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CustomersViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.customer_item, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CustomersViewHolder).bind(CustomersViewModel.customersList[position])
    }

    override fun getItemCount(): Int {
        return CustomersViewModel.customersList.size
    }

    open fun submitList(customersList: List<Customer>) {
        CustomersViewModel.customersList = customersList
    }

    open class CustomersViewHolder(
        itemView: View,
    ) : RecyclerView.ViewHolder(itemView) {

        private val customerName = itemView.findViewById<TextView>(R.id.customer_name)
        private val customerImage = itemView.findViewById<ImageView>(R.id.customer_image)

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
                    R.id.add_to_favorite -> addToFavorite(customer)

                    R.id.delete -> deleteCustomer(customer)

                    else -> false
                }
            }

            popupMenu.inflate(R.menu.customers_menu_items)

            setupMenuIcons(popupMenu)
        }


        private fun addToFavorite(customer: Customer): Boolean {
            customer.favorite = true
            Toast.makeText(itemView.context,
                "${customer.customerName} added to favorite",
                Toast.LENGTH_SHORT).show()

            return true
        }


        private fun deleteCustomer(customer: Customer): Boolean {
            val index = CustomersViewModel.customersList.indexOf(customer)

            return try {
                (CustomersViewModel.customersList as ArrayList).removeAt(index)
                bindingAdapter?.notifyItemRemoved(index)
                true
            } catch (error: Exception) {
                Toast.makeText(itemView.context,
                    "Failed to delete",
                    Toast.LENGTH_SHORT).show()
                false
            }

        }


        private fun setupMenuIcons(popupMenu: PopupMenu): Boolean {
            return try {
                val fieldMPopup = PopupMenu::class.java.getDeclaredField("mPopup")
                fieldMPopup.isAccessible = true
                val mPopup = fieldMPopup.get(popupMenu)
                mPopup.javaClass
                    .getDeclaredMethod("setForceShowIcon", Boolean::class.java)
                    .invoke(mPopup, true)
                true
            } catch (error: Exception) {
                println(error.stackTrace)
                false
            } finally {
                popupMenu.show()
            }
        }
    }
}

