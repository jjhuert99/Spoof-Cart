package com.example.spoofcart

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.spoofcart.network.ShoppingItem
import com.example.spoofcart.sharedpref.CartItem
import com.example.spoofcart.ui.ShoppingCartAdapter

@BindingAdapter("shoppingItems")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<ShoppingItem>?){
    val adapter = recyclerView.adapter as ShoppingItemAdapter
    adapter.submitList(data)
}

@BindingAdapter("cartItems")
fun bindShoppingCart(recyclerView: RecyclerView, data: List<CartItem>?){
    val adapter = recyclerView.adapter as ShoppingCartAdapter
    adapter.submitList(data)
}
