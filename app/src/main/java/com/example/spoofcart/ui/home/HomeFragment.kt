package com.example.spoofcart.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.spoofcart.Clicked
import com.example.spoofcart.R
import com.example.spoofcart.ShoppingItemAdapter
import com.example.spoofcart.databinding.HomeFragmentBinding
import com.example.spoofcart.sharedpref.CartItem
import com.example.spoofcart.sharedpref.SharedPrefImpl
import com.google.android.material.dialog.MaterialAlertDialogBuilder
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
        binding.itemRV.adapter = ShoppingItemAdapter(Clicked { post ->
            MaterialAlertDialogBuilder(requireContext())
                .setTitle(getString(R.string.product_desc))
                .setMessage(post.description)
                .setPositiveButton("Add") { dialog, which ->
                    Toast.makeText(requireContext(), "Added To Cart", Toast.LENGTH_SHORT).show()
                    val cartItem = CartItem(
                        post.id,
                        post.title,
                        post.price,
                        post.category
                    )
                    //call SharedPref class add function and pass CartItem
                    viewModel.addOneItemSharedPrefs(cartItem)
                    viewModel.checkAdd = true

                }
                .setNegativeButton("Cancel") { dialog, which ->
                }
                .show()
        })
        viewModel.doneNav()

        binding.shoppingCartFab.setOnClickListener {
            viewModel.justNav()
            if (viewModel.navYet.value == true) {
                if (viewModel.checkAdd) {
                    viewModel.addWholeListSharedPrefs("ShoppingList")
                }
                this.findNavController()
                    .navigate(HomeFragmentDirections.actionHomeFragmentToShoppingCartFragment())
            }
        }
        return binding.root
    }
}
