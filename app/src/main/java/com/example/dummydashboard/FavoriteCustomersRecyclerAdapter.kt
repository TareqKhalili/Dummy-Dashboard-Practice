package com.example.dummydashboard

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
import com.example.dummydashboard.models.Customer

class FavoriteCustomersRecyclerAdapter :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return FavoriteCustomersViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.customer_item, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as FavoriteCustomersViewHolder).bind(CustomersViewModel.favoriteCustomersList[position])
    }

    override fun getItemCount(): Int {
        return CustomersViewModel.favoriteCustomersList.size
    }


    fun submitList(customersList: List<Customer>) {
        CustomersViewModel.favoriteCustomersList = customersList
    }


    open class FavoriteCustomersViewHolder(
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
                        R.id.remove_from_favorite -> {
                            customer.favorite = !customer.favorite
                            val index = CustomersViewModel.favoriteCustomersList.indexOf(customer)
                            (CustomersViewModel.favoriteCustomersList as ArrayList).removeAt(index)
                            bindingAdapter?.notifyItemRemoved(index)

                            Toast.makeText(itemView.context,
                                "${customer.customerName} Removed from to favorite",
                                Toast.LENGTH_SHORT).show()
                            true
                        }

                        R.id.delete -> {
                            var index = CustomersViewModel.customersList.indexOf(customer)
                            delete(index)
                            index = CustomersViewModel.favoriteCustomersList.indexOf(customer)
                            (CustomersViewModel.favoriteCustomersList as ArrayList).removeAt(index)
                            bindingAdapter?.notifyItemRemoved(index)
                            true
                        }

                        else -> {
                            false
                        }
                    }
                }
                popupMenu.inflate(R.menu.favorite_menu_items)

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