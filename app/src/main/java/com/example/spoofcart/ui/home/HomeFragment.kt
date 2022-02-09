package com.example.spoofcart.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.spoofcart.Clicked
import com.example.spoofcart.ShoppingItemAdapter
import com.example.spoofcart.databinding.HomeFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = HomeFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.itemRV.adapter = ShoppingItemAdapter(Clicked{

        })
        viewModel.doneNav()

        binding.shoppingCartFab.setOnClickListener{
            viewModel.justNav()
            if(viewModel.navYet.value == true) {
                this.findNavController()
                    .navigate(HomeFragmentDirections.actionHomeFragmentToShoppingCartFragment())
            }
        }
        return binding.root
    }


}
