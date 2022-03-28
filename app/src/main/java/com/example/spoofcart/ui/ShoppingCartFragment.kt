package com.example.spoofcart.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.spoofcart.databinding.ShoppingCartFragmentBinding
import com.example.spoofcart.sharedpref.SharedPrefImpl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShoppingCartFragment : Fragment() {

    val viewModel: ShoppingCartViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //var sharedPreferences = SharedPrefImpl(requireContext().applicationContext)


        val binding = ShoppingCartFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        //sharedPreferences.getString("NEW_FILE", "")?.let { viewModel.getCartItems(it) }
        viewModel.getShoppingList()
        binding.shoppingCartRv.adapter = ShoppingCartAdapter()

        return binding.root
    }
}
