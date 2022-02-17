package com.example.dummydashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dummydashboard.databinding.FragmentFavoriteBinding

class FavoriteFragment : Fragment() {
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private lateinit var favoriteCustomersRecyclerAdapter: FavoriteCustomersRecyclerAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        val view = binding.root

        initRecyclerView()
        refreshDataSet()

        return view
    }

    private fun refreshDataSet() {
        val data = CustomersViewModel.customersList.filter { it.favorite }
        favoriteCustomersRecyclerAdapter.submitList(data)
    }

    private fun initRecyclerView() {
        binding.favoriteCustomersList.apply {
            favoriteCustomersRecyclerAdapter = FavoriteCustomersRecyclerAdapter()
            adapter = favoriteCustomersRecyclerAdapter
        }
    }
}