package com.example.dummydashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dummydashboard.databinding.FragmentCustomersBinding

class CustomersFragment : Fragment() {
    private var _binding: FragmentCustomersBinding? = null
    private val binding get() = _binding!!
    private lateinit var customersAdapter: CustomersRecyclerAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCustomersBinding.inflate(inflater, container, false)
        val view = binding.root

        initRecyclerView()
        customersAdapter.submitList(CustomersViewModel.customersList)

        return view
    }

    private fun initRecyclerView() {
        binding.customersList.apply {
            customersAdapter = CustomersRecyclerAdapter()
            adapter = customersAdapter
        }
    }

}