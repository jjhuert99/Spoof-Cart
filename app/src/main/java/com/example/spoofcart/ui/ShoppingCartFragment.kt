package com.example.spoofcart.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.example.spoofcart.databinding.ShoppingCartFragmentBinding
import com.example.spoofcart.sharedpref.CartItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.AndroidEntryPoint
import java.lang.reflect.Type


@AndroidEntryPoint
class ShoppingCartFragment : Fragment() {

    val viewModel: ShoppingCartViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val masterKey = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        val sharedPreferences = EncryptedSharedPreferences.create(
            "FILE",
            masterKey,
            requireContext().applicationContext,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

        val gson = Gson()
        val json = sharedPreferences.getString("ShoppingList","")
        val t = object : TypeToken<ArrayList<CartItem>>(){}.type
        val list = gson.fromJson<List<CartItem>>(json,t)


        //val editor = sharedPreferences.all

        val binding = ShoppingCartFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.getCartItems(list)
        binding.shoppingCartRv.adapter = ShoppingCartAdapter()

        /*for(key in editor.keys){
            Log.d("JJJ", "${editor[key]}")
        }*/
        return binding.root
    }
}
