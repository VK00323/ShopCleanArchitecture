package com.example.shop.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shop.data.ShopListRepositoryImpl
import com.example.shop.domain.DeleteShopItemUseCase
import com.example.shop.domain.EditShopItemUseCase
import com.example.shop.domain.GetShopListUseCase
import com.example.shop.domain.ShopItem
import javax.inject.Inject

class ShopListViewModel @Inject constructor(shopListRepositoryImpl: ShopListRepositoryImpl) :
    ViewModel() {

    private val getShopListUseCase = GetShopListUseCase(shopListRepositoryImpl)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(shopListRepositoryImpl)
    private val editShopItemUseCase = EditShopItemUseCase(shopListRepositoryImpl)

    val shopList = getShopListUseCase.getShopList()


//    fun getShopList() {
//        shopList.value = getShopListUseCase.getShopList()
//    }

    fun deleteShopItem(shopItem: ShopItem){
        deleteShopItemUseCase.deleteShopItem(shopItem)
//        getShopList()
    }

    fun changeShopItem(shopItem: ShopItem){
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(shopItem)
//        getShopList()
    }



    @Suppress("UNCHECKED_CAST")
    class FactoryViewModel @Inject constructor(
        private val shopListRepositoryImpl: ShopListRepositoryImpl
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(ShopListRepositoryImpl::class.java)
                .newInstance(shopListRepositoryImpl)
        }
    }

}