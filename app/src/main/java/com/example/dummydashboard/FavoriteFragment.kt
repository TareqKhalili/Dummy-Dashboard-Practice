package com.example.dummydashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.dummydashboard.adapters.FavoriteCustomersRecyclerAdapter
import com.example.dummydashboard.databinding.FragmentFavoriteBinding
import com.example.dummydashboard.models.CustomersViewModel

class FavoriteFragment : Fragment() {
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CustomersViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        val view = binding.root

        val adapter = FavoriteCustomersRecyclerAdapter(viewModel)
        binding.favoriteCustomersList.adapter = adapter

        viewModel.customers.observe(viewLifecycleOwner) {
            it?.let {
                adapter.submitList(it.filter { customer -> customer.favorite })
            }
        }

        return view
    }
}