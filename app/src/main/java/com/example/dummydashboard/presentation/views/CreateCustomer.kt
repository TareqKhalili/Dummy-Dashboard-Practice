package com.example.dummydashboard.presentation.views

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.dummydashboard.R
import com.example.dummydashboard.databinding.FragmentCreateCustomerBinding
import com.example.dummydashboard.databinding.FragmentCustomersBinding
import com.example.dummydashboard.models.Customer
import com.example.dummydashboard.presentation.viewModels.CustomersViewModel
import com.example.dummydashboard.repos.FakeRepo


class CreateCustomer : Fragment() {
    private var _binding: FragmentCreateCustomerBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CustomersViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateCustomerBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.capturedImage.setImageURI(arguments?.get("ImageUri") as Uri)

        binding.addCustomerButton.setOnClickListener {
            val customer = Customer(
                binding.customerName.text.toString(),
                arguments?.get("ImageUri").toString(),
                binding.favoriteCheckbox.isChecked
            )

            viewModel.addCustomer(customer)

            findNavController().navigate(R.id.action_createCustomer_to_customersFragment)
        }

        return view
    }


}