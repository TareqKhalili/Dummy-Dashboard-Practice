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
import com.example.dummydashboard.models.CustomersViewModel
import com.example.dummydashboard.R
import com.example.dummydashboard.models.Customer


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

        open fun bind(customer: Customer) {
            customerName.text = customer.customerName

            // using glide to load images
            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(customer.image)
                .into(customerImage)

            itemView.setOnLongClickListener {
                val popupMenu = PopupMenu(itemView.context, it)

                popupMenu.setOnMenuItemClickListener { item ->
                    when (item.itemId) {
                        R.id.add_to_favorite -> {
                            customer.favorite = !customer.favorite

                            Toast.makeText(itemView.context,
                                "${customer.customerName} added to favorite",
                                Toast.LENGTH_SHORT).show()
                            true
                        }

                        R.id.delete -> {
                            val index = CustomersViewModel.customersList.indexOf(customer)
                            delete(index)
                        }

                        else -> {
                            false
                        }
                    }
                }
                popupMenu.inflate(R.menu.customers_menu_items)

                try {
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

        open fun delete(index: Int): Boolean {
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

    }
}

