package com.example.shop.domain;

import androidx.lifecycle.LiveData
import javax.inject.Inject

public class GetShopItemFromIdUseCase @Inject constructor(private val shopListRepository: ShopListRepository) {

    fun getShopItemFromId( id:Int) :  ShopItem {
        return shopListRepository.getShopItemFromId(id)
    }
}
