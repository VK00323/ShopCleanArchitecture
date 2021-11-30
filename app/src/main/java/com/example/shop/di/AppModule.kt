package com.example.shop.di

import com.example.shop.data.ShopListRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideShopListRepositoryImpl(): ShopListRepositoryImpl {
        return ShopListRepositoryImpl
    }

}