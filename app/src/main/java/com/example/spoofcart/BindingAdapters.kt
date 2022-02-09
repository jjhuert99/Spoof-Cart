package com.example.spoofcart

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.spoofcart.network.ShoppingItem

@BindingAdapter("shoppingItems")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<ShoppingItem>?){
    val adapter = recyclerView.adapter as ShoppingItemAdapter
    adapter.submitList(data)
}
