package com.example.shop.domain;

import javax.inject.Inject

public class EditShopItemUseCase @Inject constructor(private val shopListRepository: ShopListRepository) {

    fun editShopItem(shopItem: ShopItem)  {
        shopListRepository.editShopItem(shopItem)
    }

}
