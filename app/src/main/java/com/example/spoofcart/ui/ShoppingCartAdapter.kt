package com.example.spoofcart.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.spoofcart.databinding.HolderCartItemBinding
import com.example.spoofcart.sharedpref.CartItem

class ShoppingCartAdapter: ListAdapter < CartItem, ShoppingCartAdapter.CartHolder >(DiffCallBack) {
    class CartHolder(private var binding: HolderCartItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CartItem){
            binding.cartItem = item
        }
    }

    object DiffCallBack: DiffUtil.ItemCallback< CartItem >(){
        override fun areItemsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartHolder {
        return CartHolder(HolderCartItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: CartHolder, position: Int) {
        val newItem = getItem(position)
        holder.bind(getItem(position))
        holder.bind(newItem)
    }
}
