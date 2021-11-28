package com.example.shop.di

import com.example.shop.presentation.ShopListActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(shopListActivity: ShopListActivity)

}