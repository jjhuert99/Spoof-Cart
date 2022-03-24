package com.example.spoofcart.ui

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spoofcart.network.ShoppingRepo
import com.example.spoofcart.sharedpref.CartItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class ShoppingCartViewModel @Inject constructor(
    private val app: Application,
    private val ShoppingRepo: ShoppingRepo,
    private val dispatcher: Dispatchers
): ViewModel() {
    var shoppingCart = MutableLiveData<List<CartItem>?>()

    fun getCartItems(list: List<CartItem>){
        shoppingCart.value = list
    }
}
