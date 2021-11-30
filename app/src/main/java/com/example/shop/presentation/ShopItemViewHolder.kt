package com.example.shop.presentation

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shop.R
import com.example.shop.domain.ShopItem

class ShopItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val textViewName: TextView = itemView.findViewById(R.id.textViewName)
    val textViewCount: TextView = itemView.findViewById(R.id.textViewCount)
    var shopItem: ShopItem? = null
}