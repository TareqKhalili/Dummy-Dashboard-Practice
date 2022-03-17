package com.example.dummydashboard.presentation.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavGraph
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.dummydashboard.R
import com.example.dummydashboard.adapters.CustomersRecyclerAdapter
import com.example.dummydashboard.databinding.FragmentCustomersBinding
import com.example.dummydashboard.presentation.viewModels.CustomersViewModel

class CustomersFragment : Fragment() {
    private var _binding: FragmentCustomersBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CustomersViewModel by activityViewModels()
    private lateinit var adapter: CustomersRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCustomersBinding.inflate(inflater, container, false)
        val view = binding.root

        adapter = CustomersRecyclerAdapter(viewModel)
        binding.customersList.adapter = adapter

        viewModel.customers.observe(viewLifecycleOwner) {
            it?.let {
                adapter.submitList(it)
            }
        }

        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        }

        binding.addCustomer.setOnClickListener {
            view.findNavController().navigate(R.id.action_customersFragment_to_cameraFragment)
        }

        return view
    }
}