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
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.example.spoofcart.Clicked
import com.example.spoofcart.R
import com.example.spoofcart.ShoppingItemAdapter
import com.example.spoofcart.databinding.HomeFragmentBinding
import com.example.spoofcart.sharedpref.CartItem
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val gson = Gson()

        val masterKey = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        val sharedPreferences = EncryptedSharedPreferences.create(
            "FILE",
            masterKey,
            requireContext().applicationContext,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
        val editor = sharedPreferences.edit()


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
                    val json = gson.toJson(cartItem)
                    editor.putString(post.id.toString(), json)
                    editor.apply()
                }
                .setNegativeButton("Cancel") { dialog, which ->
                }
                .show()
        })
        viewModel.doneNav()

        binding.shoppingCartFab.setOnClickListener {
            viewModel.justNav()
            if (viewModel.navYet.value == true) {
                this.findNavController()
                    .navigate(HomeFragmentDirections.actionHomeFragmentToShoppingCartFragment())
            }
        }
        return binding.root
    }


}
