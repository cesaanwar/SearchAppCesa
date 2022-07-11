package com.cesa.searchappcesa.domain.usecase

import com.cesa.searchappcesa.data.Result
import com.cesa.searchappcesa.data.SearchRepository
import com.cesa.searchappcesa.domain.mapper.ShopMapper
import com.cesa.searchappcesa.search.shops.ShopUIModel
import javax.inject.Inject

class GetShopsUseCase @Inject constructor(
    private val repository: SearchRepository,
    private val mapper: ShopMapper
) {

    suspend fun getShops(query: String, start: Int): Result<List<ShopUIModel>> {
        try {
            val res = repository.getShopsFromRemote(query, start)
            val uiModels = mapper.getShopUIModelFromResponse(res)
            return Result.Success(uiModels)
        } catch (e: Exception) {
            return Result.Error<List<ShopUIModel>>(mutableListOf(), e)
        }
    }

}