package com.cesa.searchappcesa.domain.mapper

import com.cesa.searchappcesa.domain.response.ShopsResponse
import com.cesa.searchappcesa.search.shops.ShopUIModel
import javax.inject.Inject

class ShopMapper @Inject constructor() {

    fun getShopUIModelFromResponse(shopResponse: ShopsResponse?): List<ShopUIModel> {
        val res = mutableListOf<ShopUIModel>()
        shopResponse?.data?.aceSearchShop?.shops?.forEach {
            res.add(
                ShopUIModel(
                    it.shopId,
                    it.shopName,
                    it.shopLocation,
                    it.shopImage
                )
            )
        }
        return res
    }

}