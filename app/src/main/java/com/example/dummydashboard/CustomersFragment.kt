package com.example.dummydashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.dummydashboard.adapters.CustomersRecyclerAdapter
import com.example.dummydashboard.databinding.FragmentCustomersBinding
import com.example.dummydashboard.models.CustomersViewModel

class CustomersFragment : Fragment() {
    private var _binding: FragmentCustomersBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: CustomersViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCustomersBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModel = ViewModelProvider(this)[CustomersViewModel::class.java]

        val adapter = CustomersRecyclerAdapter(viewModel)
        binding.customersList.adapter = adapter

        viewModel.customers.observe(viewLifecycleOwner) {
            it?.let {
                adapter.submitList(it)
                binding.progressBar.isVisible = false
            }
        }

        return view
    }
}