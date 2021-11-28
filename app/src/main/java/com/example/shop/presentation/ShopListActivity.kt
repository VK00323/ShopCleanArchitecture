package com.example.shop.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as App).appComponent.inject(this)
        val viewModelFactory = ShopListViewModel.FactoryViewModel(shopListRepositoryImpl)
        val viewModel = ViewModelProvider(this, viewModelFactory)[ShopListViewModel::class.java]
        recyclerView = findViewById(R.id.recyclerView)
        val adapter = ShopListAdapter()
        recyclerView.adapter = adapter
        viewModel.shopList.observe(this, Observer {
            adapter.submitList(it)
            if (count <= 15) {
                count++
                val item = it[0]
                viewModel.deleteShopItem(item)
            }
            Log.d("TEST", it.toString())
        })
//        viewModel.getShopList()


    }

}