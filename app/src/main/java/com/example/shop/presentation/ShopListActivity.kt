package com.example.shop.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.shop.R
import com.example.shop.data.ShopListRepositoryImpl
import com.example.shop.di.App
import javax.inject.Inject


class ShopListActivity : AppCompatActivity(R.layout.activity_main) {
    @Inject
    lateinit var shopListRepositoryImpl: ShopListRepositoryImpl
    lateinit var recyclerView: RecyclerView
    var count = 0
    val adapter = ShopListAdapter()
    lateinit var viewModel: ShopListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as App).appComponent.inject(this)
        supportActionBar?.hide()
        val viewModelFactory = ShopListViewModel.FactoryViewModel(shopListRepositoryImpl)
        viewModel = ViewModelProvider(this, viewModelFactory)[ShopListViewModel::class.java]
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.adapter = adapter
        recyclerView.recycledViewPool.setMaxRecycledViews(
            ShopListAdapter.VIEW_TYPE_ENABLED,
            ShopListAdapter.MAX_SIZE_POOL
        )
        recyclerView.recycledViewPool.setMaxRecycledViews(
            ShopListAdapter.VIEW_TYPE_DISABLED,
            ShopListAdapter.MAX_SIZE_POOL
        )
        viewModel.shopList.observe(this, Observer {
            adapter.submitList(it)
            if (count <= 15) {
                count++
                val item = it[0]
                viewModel.deleteShopItem(item)
            }
            Log.d("TEST", it.toString())
        })

        setupSwipeListener()
        setupOnLongClickListener()
        setupOnClickListener()
    }

    private fun setupSwipeListener() {
        val callBack =
            object : ItemTouchHelper.SimpleCallback(ItemTouchHelper.LEFT, ItemTouchHelper.RIGHT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    Log.d("TEST", "onMove")
                    return true
                }

                override fun onSwiped(
                    viewHolder: RecyclerView.ViewHolder,
                    direction: Int
                ) {
                    Log.d("TEST", "onSwipe")
                    val position = viewHolder.adapterPosition
                    val item = adapter.currentList[position]
                    viewModel.deleteShopItem(item)
                }
            }
        val itemTouchHelper = ItemTouchHelper(callBack)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private fun setupOnClickListener() {
        adapter.onCLickListener = {
            Log.d("TEST", it.toString())
        }
    }

    private fun setupOnLongClickListener() {
        adapter.onShopItemLongClickListener = {
            viewModel.changeShopItem(it)
        }
    }
}
