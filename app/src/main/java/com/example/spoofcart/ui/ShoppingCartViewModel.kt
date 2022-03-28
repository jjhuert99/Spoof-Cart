package com.example.spoofcart.ui

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spoofcart.network.ShoppingRepo
import com.example.spoofcart.sharedpref.CartItem
import com.example.spoofcart.sharedpref.SharedPref
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class ShoppingCartViewModel @Inject constructor(
    private val app: Application,
    private val sharedPrefs: SharedPref
): ViewModel() {
    var shoppingCart = MutableLiveData<List<CartItem>?>()

    fun getShoppingList(){
        sharedPrefs.getString("NEW_FILE","")?.let { getCartItems(it) }
    }

    fun getCartItems(list: List<CartItem>){
        shoppingCart.value = list
    }
}
