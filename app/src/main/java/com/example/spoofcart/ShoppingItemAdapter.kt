package com.example.spoofcart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.spoofcart.databinding.HolderShoppingItemBinding
import com.example.spoofcart.network.ShoppingItem

class ShoppingItemAdapter(val clickListener: Clicked)
    : ListAdapter<ShoppingItem, ShoppingItemAdapter.ItemViewHolder>(DiffCallback) {

    class ItemViewHolder(private var binding: HolderShoppingItemBinding)
        : RecyclerView.ViewHolder(binding.root) {
            fun bind(item: ShoppingItem, clickListener: Clicked){
                binding.item = item
                binding.click = clickListener
                binding.executePendingBindings()
            }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<ShoppingItem>() {
        override fun areItemsTheSame(oldItem: ShoppingItem, newItem: ShoppingItem): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: ShoppingItem, newItem: ShoppingItem): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(HolderShoppingItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(getItem(position), clickListener)
        holder.bind(item, clickListener)
    }
}
class Clicked(val clickListener: (postDesc: String) -> Unit){
    fun onClick(item: ShoppingItem) = clickListener(item.description)
}

