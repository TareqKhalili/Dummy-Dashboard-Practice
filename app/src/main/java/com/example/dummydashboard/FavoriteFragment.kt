package com.example.dummydashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.dummydashboard.adapters.FavoriteCustomersRecyclerAdapter
import com.example.dummydashboard.databinding.FragmentFavoriteBinding
import com.example.dummydashboard.models.CustomersViewModel

class FavoriteFragment : Fragment() {
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: CustomersViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModel = ViewModelProvider(this)[CustomersViewModel::class.java]

        val adapter = FavoriteCustomersRecyclerAdapter(viewModel)
        binding.favoriteCustomersList.adapter = adapter


        viewModel.favoriteCustomers.observe(viewLifecycleOwner) {
            it?.let {
                adapter.submitList(it)
            }
        }

        return view
    }
}