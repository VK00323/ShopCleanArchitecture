package com.example.shop.domain

import javax.inject.Inject

class GetShopItemFromIdUseCase @Inject constructor(private val shopListRepository: ShopListRepository) {

    fun getShopItemFromId(id: Int): ShopItem {
        return shopListRepository.getShopItemFromId(id)
    }
}
