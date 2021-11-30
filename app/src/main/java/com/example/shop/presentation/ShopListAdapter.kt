package com.example.shop.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.shop.R
import com.example.shop.domain.ShopItem

class ShopListAdapter : ListAdapter<ShopItem, ShopItemViewHolder>(ShopListDiffCallback()) {

    var onShopItemLongClickListener: ((ShopItem) -> Unit)? = null
    var onCLickListener: ((ShopItem) -> Unit)? = null
    var count = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        Log.d("TEST", "onCreateViewHolder ${++count}")
        val layout = when (viewType) {
            VIEW_TYPE_DISABLED -> R.layout.item_disabled
            VIEW_TYPE_ENABLED -> R.layout.item_enabled
            else -> throw RuntimeException("Unknown ViewType :$viewType")
        }
        val item = LayoutInflater.from(parent.context).inflate(
            layout,
            parent,
            false
        )
        return ShopItemViewHolder(item)
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {

        val currentItem = currentList[position]
        holder.textViewName.text = currentItem.name
        holder.textViewCount.text = currentItem.count.toString()
        holder.itemView.setOnClickListener {
            onCLickListener?.invoke(currentItem)
        }

        holder.itemView.setOnLongClickListener {
            onShopItemLongClickListener?.invoke(currentItem)
            Log.d("TEST", "Long$position")
            true
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = currentList[position]
        return if (item.enabled) {
            VIEW_TYPE_ENABLED
        } else {
            VIEW_TYPE_DISABLED
        }
    }

    override fun getItemCount(): Int = currentList.size

    companion object {
        const val VIEW_TYPE_ENABLED = 1
        const val VIEW_TYPE_DISABLED = 0
        const val MAX_SIZE_POOL = 10
    }

}