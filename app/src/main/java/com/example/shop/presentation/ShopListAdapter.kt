package com.example.shop.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.shop.R
import com.example.shop.domain.ShopItem

class ShopListAdapter : ListAdapter<ShopItem, ShopListAdapter.ShopListViewHolder>(DiffCallBack) {

    inner class ShopListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewName: TextView = itemView.findViewById<TextView>(R.id.textViewName)
        val textViewCount: TextView = itemView.findViewById<TextView>(R.id.textViewCount)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopListViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.item_item, parent, false)
        return ShopListViewHolder(item)
    }

    override fun onBindViewHolder(holder: ShopListViewHolder, position: Int) {
        val currentItem = currentList[position]
        holder.textViewName.text = currentItem.name
        holder.textViewCount.text = currentItem.count.toString()


    }

    override fun getItemCount(): Int = currentList.size

    private object DiffCallBack : DiffUtil.ItemCallback<ShopItem>() {

        override fun areItemsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
            return oldItem.id ==newItem.id
        }

        override fun areContentsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
            return oldItem ==newItem
        }

    }

}